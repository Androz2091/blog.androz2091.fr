import { defineConfig } from 'astro/config';
import { readFileSync, readdirSync, existsSync } from 'fs';
import { join } from 'path';
import mdx from '@astrojs/mdx';
import sitemap from '@astrojs/sitemap';

function getUnlistedSlugs() {
  const blogDir = './src/content/blog';
  if (!existsSync(blogDir)) return [];
  const slugs = new Set();
  for (const entry of readdirSync(blogDir, { withFileTypes: true })) {
    if (!entry.isDirectory()) continue;
    const dir = join(blogDir, entry.name);
    for (const file of readdirSync(dir)) {
      if (!/\.(md|mdx)$/.test(file)) continue;
      const content = readFileSync(join(dir, file), 'utf-8');
      const fm = content.match(/^---\s*\n([\s\S]+?)\n---/);
      if (fm && /^unlisted:\s*true\s*$/m.test(fm[1])) {
        slugs.add(entry.name);
        break;
      }
    }
  }
  return [...slugs];
}

const unlistedSlugs = getUnlistedSlugs();

export default defineConfig({
  site: 'https://blog.androz2091.fr',
  trailingSlash: 'always',
  compressHTML: true,
  build: {
    format: 'directory',
  },
  integrations: [
    mdx(),
    sitemap({
      filter: (page) => !unlistedSlugs.some((slug) => page.includes(`/${slug}/`)),
    }),
  ],
});
