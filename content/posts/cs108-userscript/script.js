// ==UserScript==
// @name        (CS-108) Better Reader
// @namespace   Violentmonkey Scripts
// @match       https://cs108.epfl.ch/p/*.html
// @grant       none
// @version     1.0
// @author      Androz2091
// @description 3/1/2024, 9:39:37 AM
// ==/UserScript==

const conseilsDeProg = new Set();
const methods = new Set();

// first, make methods bigger
const listItems = document.querySelectorAll('li');
for (listItem of listItems) {
    // are we in a conseils de programmation ?
    const parentDiv = listItem.parentNode.parentNode;
    const isConseilsDeProg = parentDiv.id.includes('outline-container') && parentDiv.children[0].innerHTML.includes('Conseils de programmation');
    if (isConseilsDeProg) conseilsDeProg.add(parentDiv);

    const firstCode = listItem.querySelector('code:first-of-type');
    if (firstCode && !isConseilsDeProg) {
        firstCode.style.backgroundColor = '#808080';
        firstCode.style.color = 'white';

        console.log('[METHODE] définition de méthode trouvée, ' + firstCode.innerText);
        methods.add(firstCode);
    }
}

conseilsDeProg.forEach((parentDiv) => {

  const titles = parentDiv.querySelectorAll('li > code:first-of-type');
  for (title of titles) {
    console.log('[CONSEIL] conseil trouvé pour la méthode ' + title.innerText);
    const id = title.parentNode.children[0].id;

    for (method of methods) {
      if (method.innerText.includes(title.innerText)) {
        console.log('[MATCHING] conseil pour ' + title.innerText + ' matched');
        const a = document.createElement('a');
        a.innerHTML = '<b> (voir les conseils de programmation) </b>';
        a.href = '#' + id;
        method.parentNode.appendChild(a);
      }
    }
  }

});
