import type { APIRoute } from 'astro';
import { getAllPosts, postUrl } from '../lib/posts';

export const GET: APIRoute = async ({ site }) => {
  const all = await getAllPosts();
  const unlisted = all.filter((p) => p.data.unlisted);

  const lines = ['User-agent: *'];
  for (const post of unlisted) {
    lines.push(`Disallow: ${postUrl(post)}`);
  }
  if (site) {
    lines.push('');
    lines.push(`Sitemap: ${new URL('sitemap-index.xml', site)}`);
  }

  return new Response(lines.join('\n') + '\n', {
    headers: { 'Content-Type': 'text/plain' },
  });
};
