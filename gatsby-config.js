module.exports = {
  plugins: [
    `gatsby-theme-blog-darkmode`,
    {
      resolve: `gatsby-theme-blog`,
      options: {},
    },
  ],
  // Customize your site metadata:
  siteMetadata: {
    title: `Androz2091's Blog`,
    author: `Androz2091`,
    siteUrl: 'https://blog.androz2091.fr',
    description: 'Hey! I\'m Androz2091, and this is my blog. I sometimes find interesting things and write them here. I usually write in French but I might write in English in the future.',
    social: [
      {
        name: `Twitter`,
        url: `https://twitter.com/@2091_androz`,
      },
      {
        name: `Github`,
        url: `https://github.com/Androz2091`,
      },
    ],
  },
}
