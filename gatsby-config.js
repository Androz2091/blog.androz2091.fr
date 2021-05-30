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
    description: 'Hey! Je suis Androz2091, bienvenue sur mon blog. Je trouve parfois des choses intéressantes et je les écris ici. J\'écris habituellement en français mais je pourrais écrire en anglais à l\'avenir.',
    social: [
      {
        name: `Twitter`,
        url: `https://twitter.com/2091_androz`,
      },
      {
        name: `Github`,
        url: `https://github.com/Androz2091`,
      },
    ],
  },
}
