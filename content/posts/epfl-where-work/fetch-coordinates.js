const fs = require('fs');
const fetch = require('node-fetch');

const converter = require('./converter');

if (fs.existsSync('./rooms-coordinates.json')) {
    fs.unlinkSync('./rooms-coordinates.json');
}

fs.appendFileSync('./rooms-coordinates.json', '[');

let first = true;

fetch('https://occupancy-backend-e150a8daef31.herokuapp.com/api/rooms')
.then((res) => res.json())
.then(async (json) => {

    for (const room of json) {

        console.log('Fetching coordinates for ' + room.name);

        await fetch('https://plan.epfl.ch/search?partitionlimit=5&interface=main&routing=validated&query=' + room.name)
        .then((res) => res.json())
        .then((data) => {

            const roomCoordinates = [];

            if (data.features.length === 0) return;

            data.features
            .find((f) => f.geometry.type === 'MultiPolygon')
            .geometry.coordinates[0][0].forEach((coordinates) => {

                const { latitude, longitude } = converter(coordinates[0], coordinates[1]);
                roomCoordinates.push([latitude, longitude]);

            });

            fs.appendFileSync('./rooms-coordinates.json',  (first ? '' : ',') + JSON.stringify({
                name: room.name,
                geometry: {
                    type: 'MultiPolygon',
                    coordinates: [roomCoordinates]
                }
            }, null, 4));

            first = false;

        });
    }

    fs.appendFileSync('./rooms-coordinates.json', ']');

});
