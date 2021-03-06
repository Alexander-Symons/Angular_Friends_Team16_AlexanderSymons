window.onload = getAllConsumpties;

let btnConsumptie = document.getElementById('btnConsumptie');
btnConsumptie.onclick = addConsumptie

let btnZoek = document.getElementById('btnZoek');
btnZoek.onclick = zoekConsumptie

function getAllConsumpties () {
    fetch("/ConsumptieServlet")
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

    function zoekConsumptie() {
    let zoekwoord = document.getElementById("zoekwoord").value;

    const options = {
        method: 'POST',
        body: JSON.stringify({
            zoekwoord: zoekwoord
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    }
    fetch("ZoekServlet", options)
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
    let test = await fetch("ConsumptieServlet", options);
    location.reload()
}









function showQuotes () {
            let quote = JSON.parse(getNewQuoteRequest.responseText);

            let quoteDiv = document.getElementById("quote");
            let quoteParagraph = quoteDiv.childNodes[0];
            let quoteText = document.createTextNode(quote.text); // kan ook quote["text"]

            if (quoteParagraph == null) {
                quoteParagraph = document.createElement('p');
                quoteParagraph.appendChild(quoteText);
                quoteDiv.appendChild(quoteParagraph);
            } else {
                quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
                quoteParagraph.appendChild(quoteText);
            }
            setTimeout(getNewQuote, 1000);
}

function addQuote () {
    let quoteText = document.getElementById("quotetext").value;
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    let information = "quote=" + encodeURIComponent(quoteText);
    newQuoteRequest.open("POST", "ManageQuoteServlet", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newQuoteRequest.send(information);
}
