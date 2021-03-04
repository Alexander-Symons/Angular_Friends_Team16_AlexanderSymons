window.onload = getNewQuote;

let quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = addQuote;

let searchbutton = document.getElementById('searchbutton');
searchbutton.onclick;
// mag NIET addQuote() zijn hier
// anders wordt het maar 1 keer uitgevoerd, namelijk na het laden van de html pagina
// en het moet telkens wanneer er op de button wordt gedrukt uitgevoerd worden

let getNewQuoteRequest = new XMLHttpRequest();
// 0
// The request is not initialized.
// After you have created the XMLHttpRequest object, but before you have called the open() method.
let newQuoteRequest = new XMLHttpRequest();

function getNewQuote () {
    getNewQuoteRequest.open("GET", "ManageQuoteServlet", true);
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
            let quote = JSON.parse(getNewQuoteRequest.responseText);

            // let quoteDiv = document.getElementById("quote");
            // let quoteParagraph = quoteDiv.childNodes[0];
            // let quoteText = document.createTextNode(quote); // kan ook quote["text"]


            var options = [
                // set0 = quote.toString(set0),
                set2 = ['Option 1', 'Option 2'],
                set1 = ['First Option', 'Second Option', 'Third Option'],
            ];

            function makeUL(array) {
                // console.log(quote[0]["naam"]);
                // Create the list element:
                if (document.getElementById("list") != null) {
                    document.getElementById("list").remove();
                }


                var list = document.createElement('ul');
                list.setAttribute("id", "list")

                let i = 0
                while (quote[i] != null) {
                    // console.log(quote[i]["naam"]);

                    // Create the list item:
                    var item = document.createElement('li');


                    // Set its contents:
                    item.appendChild(document.createTextNode(quote[i]["naam"]));
                    // let element = document.createElement("button");
                    //Assign different attributes to the element.
                    // element.type = i.toString();
                    // element.onclick = searchQuote;

                    //Append the element in page (in span).
                    // item.appendChild(element);

                    let button = document.createElement("button");
                    button.textContent = "delete"
                    // searchbutton.setAttribute("test", i.toString())
                    button.setAttribute("number", i.toString())
                    button.addEventListener('click', function() {
                        deleteQuote(this.getAttribute("number"));
                    }, false);
                    item.appendChild(button)

                    let button2 = document.createElement("button");
                    button2.textContent = "edit"
                    // searchbutton.setAttribute("test", i.toString())
                    button2.setAttribute("number", i.toString())
                    button2.addEventListener('click', function() {
                        editQuote(this.getAttribute("number"));
                    }, false);
                    item.appendChild(button2)
                    // Add it to the list:
                    list.appendChild(item);
                    i++;
                }
                return list;
            }

// Add the contents of options[0] to #foo:
            document.getElementById('foo').appendChild(makeUL(options[0]));
            setTimeout(getNewQuote, 1000);
        }
    }
}

function addQuote () {
    let quoteText = document.getElementById("quotetext").value;
    console.log(quoteText)
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    let information = "quote=" + encodeURIComponent(quoteText);
    newQuoteRequest.open("POST", "ManageQuoteServlet?command=post", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newQuoteRequest.send(information);
}
function deleteQuote(i){
    console.log(i);
    newQuoteRequest.open("GET", "ManageQuoteServlet?command=delete&number="+i, true);
    newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newQuoteRequest.send();
}
function editQuote(i){
    window.location.replace("/form.html?number="+i);
    // console.log(i);
    // newQuoteRequest.open("GET", "ManageQuoteServlet?command=edit&number="+i, true);
    // newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    // newQuoteRequest.send();
}