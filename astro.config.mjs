import { defineConfig } from 'astro/config';
import mdx from '@astrojs/mdx';
import sitemap from '@astrojs/sitemap';

export default defineConfig({
  site: 'https://blog.androz2091.fr',
  trailingSlash: 'always',
  compressHTML: true,
  build: {
    format: 'directory',
  },
  integrations: [mdx(), sitemap()],
});
