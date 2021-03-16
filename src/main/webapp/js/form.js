window.onload = start;

let getNewQuoteRequest = new XMLHttpRequest();
let newQuoteRequest = new XMLHttpRequest();
let quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = editQuote;

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let t = urlParams.get('number')

function start() {
    getNewQuoteRequest.open("POST", "ConsumptieServlet?command=editpage&number="+ t , true);
    getNewQuoteRequest.onreadystatechange = showQuote;
    getNewQuoteRequest.send();
}
function showQuote(){
    if (getNewQuoteRequest.readyState == 4) {
        if (getNewQuoteRequest.status == 200) {
            let consumptie = JSON.parse(getNewQuoteRequest.responseText);
            document.getElementById("foodnaam").value = consumptie.naam;
            document.getElementById("foodbescrijving").value = consumptie.beschrijving;
            document.getElementById("foodtype").value = consumptie.type;
            document.getElementById("foodprijs").value = consumptie.prijs;
        }
    }
}
function editQuote () {
    console.log("test")
    let string = "";
    let foodname = document.getElementById("foodnaam").value;
    let fooddescription = document.getElementById("foodbescrijving").value;
    let foodtype = document.getElementById("foodtype").value;
    let foodprice  = document.getElementById("foodprijs").value;
    if(foodname === "") {
        string += "Name must be filled out \n"
    }
    if(foodtype ==="") {
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
    }else {
        // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
        let information = "foodname=" + encodeURIComponent(foodname) + "&foodtype=" + encodeURIComponent(foodtype) + "&foodprice=" + encodeURIComponent(foodprice)
            + "&fooddescription=" + encodeURIComponent(fooddescription) + "&number=" + t;
        newQuoteRequest.open("POST", "ConsumptieServlet?command=edit&number=" +t, true);
        // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
        // protocol header information
        newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        newQuoteRequest.send(information);
        newQuoteRequest.onreadystatechange = func
    }
    function func() {
        if (newQuoteRequest.readyState == 4) {
            if (newQuoteRequest.status == 200) {
                window.location.replace("/index.html");
            }
        }
    }
}