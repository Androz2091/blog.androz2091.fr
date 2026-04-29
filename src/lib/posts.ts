import { getCollection, type CollectionEntry } from 'astro:content';

export type Post = CollectionEntry<'blog'>;
export type Lang = 'fr' | 'en';

export function getCanonicalSlug(id: string): string {
  return id.replace(/\/(index|en|fr)$/, '');
}

export async function getAllPosts(): Promise<Post[]> {
  return await getCollection('blog');
}

export async function getPostsByLang(lang: Lang): Promise<Post[]> {
  const all = await getAllPosts();
  return all
    .filter((p) => p.data.lang === lang)
    .sort((a, b) => {
      const fa = a.data.featured ? 1 : 0;
      const fb = b.data.featured ? 1 : 0;
      if (fa !== fb) return fb - fa;
      return b.data.date.valueOf() - a.data.date.valueOf();
    });
}

export async function findSibling(post: Post): Promise<Post | undefined> {
  const all = await getAllPosts();
  const slug = getCanonicalSlug(post.id);
  return all.find(
    (p) => p.id !== post.id && getCanonicalSlug(p.id) === slug,
  );
}

export function postUrl(post: Post): string {
  const slug = getCanonicalSlug(post.id);
  return post.data.lang === 'en' ? `/en/${slug}/` : `/${slug}/`;
}

export function homeUrl(lang: Lang): string {
  return lang === 'en' ? '/en/' : '/';
}

export function getExcerpt(post: Post, maxChars = 140): string {
  if (post.data.excerpt) return post.data.excerpt;
  const body = post.body ?? '';
  const lines = body.split('\n');
  let inFence = false;
  for (const rawLine of lines) {
    const line = rawLine.trim();
    if (line.startsWith('```')) {
      inFence = !inFence;
      continue;
    }
    if (inFence) continue;
    if (!line) continue;
    if (line.startsWith('#')) continue;
    if (line.startsWith('>')) continue;
    if (line.startsWith('<!--')) continue;
    if (/^([*\-+]|\d+\.)\s/.test(line)) continue;
    if (line.startsWith('import ') || line.startsWith('export ')) continue;
    const plain = line
      .replace(/!\[[^\]]*\]\([^)]*\)/g, '')
      .replace(/\[([^\]]+)\]\([^)]*\)/g, '$1')
      .replace(/[*_`]/g, '')
      .replace(/\s+/g, ' ')
      .trim();
    if (!plain) continue;
    if (plain.length <= maxChars) return plain;
    const slice = plain.slice(0, maxChars);
    const lastSpace = slice.lastIndexOf(' ');
    return (lastSpace > 0 ? slice.slice(0, lastSpace) : slice) + '…';
  }
  return '';
}
