// version of getNewQuote with plain old JavaScript code only
var xHRObject = new XMLHttpRequest();

function getNewQuote () {
    $.open("POST", "GroupJSServlet", true);
    $.onreadystatechange = getData;
    $.send(null);
}

function getData () {
    if (xHRObject.status == 200) {
        if (xHRObject.readyState == 4) {
            var serverResponse = JSON.parse(xHRObject.responseText);
            var quote = serverResponse.quote;

            var quoteDiv = document.getElementById("quote");
            var quoteParagraph = quoteDiv.childNodes[0];

            if (quoteParagraph == null) {
                quoteParagraph = document.createElement('p');
                quoteParagraph.id = "quoteText";
                var quoteText = document.createTextNode(quote);
                quoteParagraph.appendChild(quoteText);
                quoteDiv.appendChild(quoteParagraph);
            }
            else {
                var quoteText = document.createTextNode(quote);
                quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
                quoteParagraph.appendChild(quoteText);
            }
            setTimeout(getNewQuote, 2000);
        }
    }
}



function send(){
    let groupname = document.getElementById("groupchat").value;
    window.location.replace("/groupchat.jsp?groupname="+groupname);
}