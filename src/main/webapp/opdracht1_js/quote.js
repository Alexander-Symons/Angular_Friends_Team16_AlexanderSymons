window.onload = getNewQuote;

let quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = addQuote;

let searchbutton = document.getElementById('searchbutton');
searchbutton.onclick = search;
// mag NIET addQuote() zijn hier
// anders wordt het maar 1 keer uitgevoerd, namelijk na het laden van de html pagina
// en het moet telkens wanneer er op de button wordt gedrukt uitgevoerd worden

let getNewQuoteRequest = new XMLHttpRequest();
// 0
// The request is not initialized.
// After you have created the XMLHttpRequest object, but before you have called the open() method.
let newQuoteRequest = new XMLHttpRequest();

function getNewQuote () {
    getNewQuoteRequest.open("GET", "ConsumptieServlet", true);
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
                let cellE = document.createElement("td");
                let cellF = document.createElement("td");

                cellA.innerHTML = consumpties[i].naam;
                cellB.innerHTML = consumpties[i].beschrijving;
                cellC.innerHTML = consumpties[i].type;
                cellD.innerHTML = consumpties[i].prijs;

                let button2 = document.createElement("button");
                button2.textContent = "edit"
                // searchbutton.setAttribute("test", i.toString())
                button2.setAttribute("number", i.toString())
                button2.addEventListener('click', function() {
                    editQuote(this.getAttribute("number"));
                }, false);
                cellE.appendChild(button2)

                let button = document.createElement("button");
                button.textContent = "delete"
                // searchbutton.setAttribute("test", i.toString())
                button.setAttribute("number", i.toString())
                button.addEventListener('click', function() {
                    deleteQuote(this.getAttribute("number"));
                }, false);
                cellF.appendChild(button)


                table.appendChild(row);
                row.appendChild(cellA);
                row.appendChild(cellB);
                row.appendChild(cellC);
                row.appendChild(cellD);
                row.appendChild(cellE);
                row.appendChild(cellF);
            }
            setTimeout(getNewQuote, 1000);
        }
    }
}

async function addQuote () {
    console.log("test")

    let foodname = document.getElementById("foodname").value;
    let fooddescription = document.getElementById("fooddescription").value;
    let foodtype = document.getElementById("foodtype").value;
    let foodprice = document.getElementById("foodprice").value;
    let string = "";
    if(foodname === "") {
        string += "Name must be filled out \n"
    }
    if(foodtype === "") {
        string += "Type must be filled out \n"
    }
    if(foodprice=== ""){
        string += "Price must be filled out \n"
    }
    if(fooddescription === ""){
        string += "Food description must be filled out \n"
    }
    if(foodname === "" || fooddescription === "" || foodtype === "" || foodprice=== ""){
        alert(string);
    }
    else { // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
        let information = "foodname=" + encodeURIComponent(foodname) + "&foodtype=" + encodeURIComponent(foodtype) + "&foodprice=" + encodeURIComponent(foodprice)
        + "&fooddescription=" + encodeURIComponent(fooddescription);
        newQuoteRequest.open("POST", "ConsumptieServlet?command=post", true);
        // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
        // protocol header information
        newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        newQuoteRequest.send(information);
    }
}
async function deleteQuote(i) {
    newQuoteRequest.open("GET", "ConsumptieServlet?command=delete&number=" + i, true);
    newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newQuoteRequest.send();
}
async function editQuote(i){
    window.location.replace("/form.html?number="+i);
    // console.log(i);
    // newQuoteRequest.open("GET", "ManageQuoteServlet?command=edit&number="+i, true);
    // newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    // newQuoteRequest.send();
}
async function search(){
    window.location.replace("/search.html")
}

