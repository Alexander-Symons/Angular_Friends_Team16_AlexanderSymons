window.onload = getAllConsumpties;

let btnConsumptie = document.getElementById('btnConsumptie');
btnConsumptie.onclick = addConsumptie


function getAllConsumpties () {
    fetch("/ConsumptieServletTeam1")
        .then(response => response.json())
        .then((data => showConsumpties(data)));
}

function showConsumpties(consumpties) {
    let table = document.getElementById("table");
    let row = document.createElement("tr");

    for (let key in consumpties[0]) {

        let h1 = document.createElement("th");
        h1.innerHTML = key;
        row.appendChild(h1);
    }
    table.appendChild(row);

    for (let i = 0; i < consumpties.length; i++) {
        let row = document.createElement("tr");
        let cellA = document.createElement("td");
        let cellB = document.createElement("td");
        let cellC = document.createElement("td");
        let cellD = document.createElement("td");

        cellA.innerHTML = consumpties[i].naam;
        cellB.innerHTML = consumpties[i].beschrijving;
        cellC.innerHTML = consumpties[i].type;
        cellD.innerHTML = consumpties[i].prijs;

        table.appendChild(row);
        row.appendChild(cellA);
        row.appendChild(cellB);
        row.appendChild(cellC);
        row.appendChild(cellD);
    }
}

async function addConsumptie() {

    let naam = document.getElementById("naam").value;
    let beschrijving = document.getElementById("beschrijving").value;
    let type = document.getElementById("type").value;
    let prijs = document.getElementById("prijs").value;

    let sNaam = encodeURIComponent(naam);
    let sBeschrijving = encodeURIComponent(beschrijving);
    let sType = encodeURIComponent(type);

    const options = {
    method: 'POST',
        body: JSON.stringify({
            naam: sNaam,
            beschrijving: sBeschrijving,
            type: sType,
            prijs: prijs
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    }
    let test = await fetch("ConsumptieServletTeam1", options);
    location.reload()
}
