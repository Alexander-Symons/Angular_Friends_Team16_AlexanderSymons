window.onload = start;

let getNewQuoteRequest = new XMLHttpRequest();
let newQuoteRequest = new XMLHttpRequest();
let quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = editQuote;

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let t = urlParams.get('number')

function start() {
    getNewQuoteRequest.open("POST", "ManageQuoteServlet?command=editpage&number="+ t , true);
    getNewQuoteRequest.onreadystatechange = showQuote;
    getNewQuoteRequest.send();
}
function showQuote(){
    if (getNewQuoteRequest.readyState == 4) {
        if (getNewQuoteRequest.status == 200) {
            let quote = JSON.parse(getNewQuoteRequest.responseText);
            console.log(quote)
            console.log("test")
        }
    }
}
function editQuote () {
    let quoteText = document.getElementById("food").value;
    console.log(quoteText)
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    let information = "quote=" + encodeURIComponent(quoteText);
    newQuoteRequest.open("POST", "ManageQuoteServlet?command=edit&number=" + t, true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newQuoteRequest.send(information);
    window.location.replace("/index.html");
}