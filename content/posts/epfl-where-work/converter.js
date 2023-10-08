const E = 2533002.05;
const N = 1152532.725;

function convert (E, N) {

    // soient X et Y nos coordonnées dans le système MN95 (donc en mètres)
    let yCentre = (E - 2600000) / 1000000;
    let xCentre = (N - 1200000) / 1000000;

    //console.log(`yCentre: ${yCentre}, xCentre: ${xCentre}`)

    // les cstes correspondent à l'observatoire de Berne, le nouveau (0,0) de notre système)
    const latitudeInitiale = 16.9023892;
    const longitudeInitiale = 2.6779094;

    // ces coefficients nous permettent de savoir
    // de cb augmenter la latitude et la longitude
    // à chaque x ou y de nos coordonnées

    const latiXCoeff = 3.238272;
    const latiYP2Coeff = -0.270978;
    const latiXP2Coeff = -0.002528;
    const latiXYP2Coeff = -0.0447;
    const latiXP3Coeff = -0.0140;

    const longiYCoeff = 4.728982;
    const longiXYCoeff = 0.791484;
    const longiXP2YCoeff = 0.1306;
    const longiYP3Coeff = -0.0436;

    // on les applique aux X et Y centrés calculés précédemment

    const nouvelleLatitude =
        latitudeInitiale
        + latiXCoeff * xCentre
        + latiYP2Coeff * (yCentre**2)
        + latiXP2Coeff * (xCentre**2)
        + latiXYP2Coeff * xCentre * (yCentre**2)
        + latiXP3Coeff * (xCentre**3);

    const nouvelleLongitude = 
        longitudeInitiale
        + longiYCoeff * yCentre
        + longiXYCoeff * xCentre * yCentre
        + longiXP2YCoeff * (xCentre**2) * yCentre
        + longiYP3Coeff * yCentre**3;

    const nouvelleLatitudeEnDegres = nouvelleLatitude * 1 / 3600 * 10000;
    const nouvelleLongitudeEnDegres = nouvelleLongitude * 1 / 3600 * 10000;

    return {
        latitude: nouvelleLatitudeEnDegres,
        longitude: nouvelleLongitudeEnDegres
    }
}

module.exports = convert;
