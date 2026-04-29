#!/usr/bin/env node
// Auto-translate French blog posts (lang: fr) to English siblings (en.mdx).
//
// For each src/content/blog/<slug>/index.md with lang: fr, this script:
//   1. Computes a SHA-256 of the frontmatter (excluding sourceHash) + body
//   2. Looks for src/content/blog/<slug>/en.mdx
//      - If `manual: true` in its frontmatter, skip (human-edited)
//      - If `sourceHash` already matches, skip (up-to-date)
//   3. Otherwise calls Claude to translate, writes the en.mdx file
//
// Usage:
//   OPENAI_API_KEY=... node scripts/translate.mjs            # all posts
//   OPENAI_API_KEY=... node scripts/translate.mjs <slug>     # one post
//
// Convention:
//   src/content/blog/<slug>/index.md  -- source (lang: fr or lang: en)
//   src/content/blog/<slug>/en.mdx    -- generated translation (when source is fr)

import { createHash } from 'node:crypto';
import { readdirSync, readFileSync, writeFileSync, existsSync, statSync } from 'node:fs';
import { join, resolve, dirname } from 'node:path';
import { fileURLToPath } from 'node:url';
import OpenAI from 'openai';

const __dirname = dirname(fileURLToPath(import.meta.url));
const REPO_ROOT = resolve(__dirname, '..');
const BLOG_DIR = resolve(REPO_ROOT, 'src/content/blog');
const MODEL = process.env.TRANSLATE_MODEL ?? 'gpt-4o';
const TARGET_SLUG = process.argv[2] ?? null;

const SYSTEM_PROMPT = `You translate French technical blog posts to English.

Rules:
- Translate the title and the body text only.
- Inside the body, do NOT modify: fenced code blocks (\`\`\`...\`\`\`), inline code (\`...\`), URLs inside markdown links, image references (e.g. ![alt](./path.png)), HTML/JSX/MDX tags or attributes, and shell command output.
- Translate image alt text inside markdown image syntax.
- Preserve all markdown structure: headings, lists, blockquotes, line breaks.
- Keep informal tone if the source is informal; technical accuracy matters.
- The body uses MDX. Leave any JSX expressions untouched.

Output ONLY a JSON object, no surrounding text or code fences. Schema:
{"title": "<English title>", "body": "<English body, complete markdown/MDX>"}`;

function splitFrontmatter(text) {
  const m = text.match(/^---\r?\n([\s\S]*?)\r?\n---\r?\n([\s\S]*)$/);
  if (!m) return null;
  return { frontmatter: m[1], body: m[2] };
}

function parseFrontmatter(yaml) {
  const obj = {};
  for (const rawLine of yaml.split(/\r?\n/)) {
    const line = rawLine.trimEnd();
    if (!line || line.startsWith('#')) continue;
    const m = line.match(/^([a-zA-Z_][\w]*):\s*(.*)$/);
    if (!m) continue;
    let val = m[2].trim();
    if (
      (val.startsWith('"') && val.endsWith('"')) ||
      (val.startsWith("'") && val.endsWith("'"))
    ) {
      val = val.slice(1, -1);
    }
    if (val === 'true') obj[m[1]] = true;
    else if (val === 'false') obj[m[1]] = false;
    else obj[m[1]] = val;
  }
  return obj;
}

function formatFrontmatter(obj) {
  const lines = ['---'];
  const order = [
    'title',
    'date',
    'image',
    'imageAlt',
    'excerpt',
    'lang',
    'sourceHash',
    'manual',
    'featured',
  ];
  const seen = new Set();
  for (const k of order) {
    if (k in obj && obj[k] !== undefined && obj[k] !== null) {
      lines.push(formatLine(k, obj[k]));
      seen.add(k);
    }
  }
  for (const [k, v] of Object.entries(obj)) {
    if (seen.has(k)) continue;
    if (v === undefined || v === null) continue;
    lines.push(formatLine(k, v));
  }
  lines.push('---');
  return lines.join('\n');
}

function formatLine(k, v) {
  if (typeof v === 'boolean' || typeof v === 'number') return `${k}: ${v}`;
  const s = String(v);
  if (/^[A-Za-z0-9._\/-]+$/.test(s) && !/^(true|false|null|yes|no)$/i.test(s)) {
    return `${k}: ${s}`;
  }
  return `${k}: ${JSON.stringify(s)}`;
}

function hashSource(frontmatter, body) {
  const fm = parseFrontmatter(frontmatter);
  delete fm.sourceHash;
  const stable = Object.keys(fm)
    .sort()
    .map((k) => `${k}=${fm[k]}`)
    .join('\n');
  return createHash('sha256').update(stable + '\n---\n' + body).digest('hex');
}

function listPostDirs() {
  return readdirSync(BLOG_DIR)
    .filter((name) => {
      const full = join(BLOG_DIR, name);
      return statSync(full).isDirectory() && existsSync(join(full, 'index.md'));
    })
    .filter((name) => !TARGET_SLUG || name === TARGET_SLUG);
}

async function callModel(client, title, body) {
  const userInput = JSON.stringify({ title, body });
  const response = await client.chat.completions.create({
    model: MODEL,
    max_tokens: 16000,
    response_format: { type: 'json_object' },
    messages: [
      { role: 'system', content: SYSTEM_PROMPT },
      { role: 'user', content: userInput },
    ],
  });
  const text = response.choices[0]?.message?.content ?? '';
  const cleaned = text.trim().replace(/^```(?:json)?\s*/, '').replace(/\s*```$/, '');
  let parsed;
  try {
    parsed = JSON.parse(cleaned);
  } catch (err) {
    throw new Error(`Model returned non-JSON output:\n${text.slice(0, 500)}`);
  }
  if (typeof parsed.title !== 'string' || typeof parsed.body !== 'string') {
    throw new Error(`Model output missing title/body fields: ${JSON.stringify(parsed).slice(0, 200)}`);
  }
  return parsed;
}

async function processPost(client, slug) {
  const sourcePath = join(BLOG_DIR, slug, 'index.md');
  const enPath = join(BLOG_DIR, slug, 'en.mdx');
  const raw = readFileSync(sourcePath, 'utf8');
  const split = splitFrontmatter(raw);
  if (!split) {
    return { slug, status: 'error', reason: 'no frontmatter' };
  }
  const fm = parseFrontmatter(split.frontmatter);
  if (fm.lang !== 'fr') {
    return { slug, status: 'skipped', reason: `lang=${fm.lang ?? 'undefined'}` };
  }
  const sourceHash = hashSource(split.frontmatter, split.body);

  if (existsSync(enPath)) {
    const enSplit = splitFrontmatter(readFileSync(enPath, 'utf8'));
    if (enSplit) {
      const enFm = parseFrontmatter(enSplit.frontmatter);
      if (enFm.manual === true) {
        return { slug, status: 'skipped', reason: 'manual=true' };
      }
      if (enFm.sourceHash === sourceHash) {
        return { slug, status: 'skipped', reason: 'sourceHash match' };
      }
    }
  }

  const translated = await callModel(client, fm.title, split.body);

  const newFm = {
    ...fm,
    title: translated.title,
    lang: 'en',
    sourceHash,
    manual: false,
  };
  const out = formatFrontmatter(newFm) + '\n\n' + translated.body.trimEnd() + '\n';
  writeFileSync(enPath, out, 'utf8');
  return { slug, status: 'translated' };
}

async function main() {
  if (!process.env.OPENAI_API_KEY) {
    console.error('OPENAI_API_KEY environment variable is required');
    process.exit(1);
  }
  const client = new OpenAI();
  const slugs = listPostDirs();
  if (slugs.length === 0) {
    console.error(TARGET_SLUG ? `No post named "${TARGET_SLUG}"` : 'No posts found');
    process.exit(1);
  }

  let translated = 0;
  let skipped = 0;
  let errored = 0;
  for (const slug of slugs) {
    process.stdout.write(`[${slug}] `);
    try {
      const result = await processPost(client, slug);
      console.log(`${result.status}${result.reason ? ` (${result.reason})` : ''}`);
      if (result.status === 'translated') translated++;
      else if (result.status === 'skipped') skipped++;
      else errored++;
    } catch (err) {
      console.log(`error: ${err.message}`);
      errored++;
    }
  }
  console.log(`\nDone. translated=${translated} skipped=${skipped} errors=${errored}`);
  if (errored > 0) process.exit(1);
}

main();
