window.onload = getWerknemers;

let addWerknemer = document.getElementById('addWerknemer');
addWerknemer.onclick = addEmployee;

let btnZoek = document.getElementById('btnZoek');
btnZoek.onclick = zoekWerknemer;

// mag NIET addQuote() zijn hier
// anders wordt het maar 1 keer uitgevoerd, namelijk na het laden van de html pagina
// en het moet telkens wanneer er op de button wordt gedrukt uitgevoerd worden

let getWerknemersRequest = new XMLHttpRequest();
let zoekWerknemersRequest = new XMLHttpRequest();
// 0
// The request is not initialized.
// After you have created the XMLHttpRequest object, but before you have called the open() method.
let addWerknemerRequest = new XMLHttpRequest();

function getWerknemers () {
    getWerknemersRequest.open("GET", "ScoreServletTeam1?command=showEmployee", true);
    // 1
    // The request has been set up.
    // After you have called the open() method, but before you have called send().
    getWerknemersRequest.onreadystatechange = showWerknemers;
    zoekWerknemersRequest.onreadystatechange = showResult;
    // mag NIET showQuotes() zijn
    // want dat wordt het maar 1 keer uitgevoerd
    // en het moet telkens wanneer de readystate van het xhr veranderd worden uitgevoerd
    getWerknemersRequest.send();
    // 2
    // The request has been sent.
    // After you have called send().
}


//@Author Robbe Jacobs
function zoekWerknemer() {
    let zoekwoord = document.getElementById("zoekwoord").value;
    zoekWerknemersRequest.open("POST", "ScoreServletTeam1?command=zoekEmployee", true)
    zoekWerknemersRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    zoekWerknemersRequest.send("zoekwoord=" + encodeURIComponent(zoekwoord));

}

//@Author Robbe Jacobs
function showResult(){
    if (zoekWerknemersRequest.readyState == 4) {
        if (zoekWerknemersRequest.status == 200) {
            let test = JSON.parse(zoekWerknemersRequest.responseText);
            let result = document.getElementById("result");
            result.innerHTML = "";

            for (let i = 0; i != test.length; i++){
                personeelsLidText = document.createTextNode(test[i].text + " met een score van   " + test[i].score);
                personeelsLidTextNode = document.createElement('p');
                personeelsLidTextNode.appendChild(personeelsLidText);
                result.appendChild(personeelsLidTextNode);
            }
        }
    }
}

// 3
// The request is in process.
// After the browser has established a communication with the server, but before the server has completed the response.

// 4
// The request is completed.
// After the request has been completed, and the response data has been completely received from the server.

// callback function
function showWerknemers () {
    if (getWerknemersRequest.readyState == 4) {
        if (getWerknemersRequest.status == 200) {
            let werknemers = JSON.parse(getWerknemersRequest.responseText);


            let personeelsLid1 = document.getElementById("personeelslid1");
            //let personeelsLid2 = document.getElementById("personeelslid2");
            //let personeelsLid3 = document.getElementById("personeelslid3");

            let personeelsLid1Text = personeelsLid1.childNodes[0];
            let personeelsLid2Text = personeelsLid1.childNodes[1];
            let personeelsLid3Text = personeelsLid1.childNodes[2];

            let personeelsLid1Naam = document.createTextNode(werknemers[0].text + ": " + werknemers[0].score);
            let personeelsLid2Naam = document.createTextNode(werknemers[1].text + ": " + werknemers[1].score);
            let personeelsLid3Naam = document.createTextNode(werknemers[2].text + ": " + werknemers[2].score);
            // kan ook quote["text"]
            //let personeelsLid1Score = document.createTextNode(werknemers[0].score);

            if (personeelsLid1Text == null) {
                personeelsLid1Text = document.createElement('p');
                personeelsLid2Text = document.createElement('p');
                personeelsLid3Text = document.createElement('p');
                personeelsLid1Text.appendChild(personeelsLid1Naam );
                personeelsLid2Text.appendChild(personeelsLid2Naam );
                personeelsLid3Text.appendChild(personeelsLid3Naam );
                personeelsLid1.appendChild(personeelsLid1Text);
                personeelsLid1.appendChild(personeelsLid2Text);
                personeelsLid1.appendChild(personeelsLid3Text);
            } else {
                personeelsLid1Text.removeChild(personeelsLid1Text.childNodes[0]);
                personeelsLid2Text.removeChild(personeelsLid2Text.childNodes[0]);
                personeelsLid3Text.removeChild(personeelsLid3Text.childNodes[0]);
                personeelsLid1Text.appendChild(personeelsLid1Naam);
                personeelsLid2Text.appendChild(personeelsLid2Naam);
                personeelsLid3Text.appendChild(personeelsLid3Naam);
            }
            setTimeout(getWerknemers, 1000);
        }
    }
}

function addEmployee () {
    let werknemerNaam = document.getElementById("werknemerNaam").value;
    let werknemerScore = document.getElementById("werknemerScore").value;
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    let information = "naam=" + encodeURIComponent(werknemerNaam) + "," + encodeURIComponent(werknemerScore);
    addWerknemerRequest.open("POST", "ScoreServletTeam1?command=addEmployee", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    addWerknemerRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    addWerknemerRequest.send(information);
}