import { defineCollection, z } from 'astro:content';
import { glob } from 'astro/loaders';

const blog = defineCollection({
  loader: glob({
    base: './src/content/blog',
    pattern: '**/*.{md,mdx}',
  }),
  schema: ({ image }) =>
    z.object({
      title: z.string(),
      date: z.coerce.date(),
      image: image().optional(),
      imageAlt: z.string().optional(),
      excerpt: z.string().optional(),
      lang: z.enum(['fr', 'en']).default('fr'),
      sourceHash: z.string().optional(),
      manual: z.boolean().default(false),
      featured: z.boolean().default(false),
    }),
});

export const collections = { blog };
