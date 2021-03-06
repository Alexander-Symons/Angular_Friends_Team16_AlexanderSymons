window.onload = getNewQuote;
let getNewQuoteRequest = new XMLHttpRequest();

let backbutton = document.getElementById('backbutton');
backbutton.onclick = back;

function getNewQuote() {
    getNewQuoteRequest.open("GET", "ConsumptieServlet?command=search", true);
    // 1
    // The request has been set up.
    // After you have called the open() method, but before you have called send().
    getNewQuoteRequest.onreadystatechange = showQuotes;
    // mag NIET showQuotes() zijn
    // want dat wordt het maar 1 keer uitgevoerd
    // en het moet telkens wanneer de readystate van het xhr veranderd worden uitgevoerd
    getNewQuoteRequest.send();
    // 2
    // The request has been sent.
    // After you have called send().
}

// 3
// The request is in process.
// After the browser has established a communication with the server, but before the server has completed the response.

// 4
// The request is completed.
// After the request has been completed, and the response data has been completely received from the server.

// callback function

function showQuotes () {
    if (getNewQuoteRequest.readyState == 4) {
        if (getNewQuoteRequest.status == 200) {
            let table = document.getElementById("table");
            table.innerHTML = "";

            let consumpties = JSON.parse(getNewQuoteRequest.responseText);
            table = document.getElementById("table");
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

                // let button2 = document.createElement("button");
                // button2.textContent = "edit"
                // // searchbutton.setAttribute("test", i.toString())
                // button2.setAttribute("number", i.toString())
                // button2.addEventListener('click', function() {
                //     editQuote(this.getAttribute("number"));
                // }, false);
                // cellE.appendChild(button2)
                //
                // let button = document.createElement("button");
                // button.textContent = "delete"
                // // searchbutton.setAttribute("test", i.toString())
                // button.setAttribute("number", i.toString())
                // button.addEventListener('click', function() {
                //     deleteQuote(this.getAttribute("number"));
                // }, false);
                // cellF.appendChild(button)


                table.appendChild(row);
                row.appendChild(cellA);
                row.appendChild(cellB);
                row.appendChild(cellC);
                row.appendChild(cellD);
            }
            setTimeout(getNewQuote, 1000);
        }
    }
}
function back(){
    window.location.replace("/index.html")
}