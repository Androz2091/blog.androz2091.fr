import rss from '@astrojs/rss';
import { SITE_TITLE, SITE_DESCRIPTION_EN } from '../../consts';
import { getPostsByLang, postUrl, getExcerpt } from '../../lib/posts';

export async function GET(context) {
  const posts = await getPostsByLang('en');
  return rss({
    title: SITE_TITLE,
    description: SITE_DESCRIPTION_EN,
    site: context.site,
    items: posts.map((post) => ({
      title: post.data.title,
      pubDate: post.data.date,
      description: getExcerpt(post),
      link: postUrl(post),
    })),
  });
}
