import rss from '@astrojs/rss';
import { SITE_TITLE, SITE_DESCRIPTION_FR } from '../consts';
import { getPostsByLang, postTitle, postUrl, getExcerpt } from '../lib/posts';

export async function GET(context) {
  const posts = await getPostsByLang('fr');
  return rss({
    title: SITE_TITLE,
    description: SITE_DESCRIPTION_FR,
    site: context.site,
    items: posts.map((post) => ({
      title: postTitle(post, 'fr'),
      pubDate: post.data.date,
      description: getExcerpt(post),
      link: postUrl(post, 'fr'),
    })),
  });
}
