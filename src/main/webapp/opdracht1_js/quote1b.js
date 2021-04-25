window.onload = getNewQuote;

let quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = addConsumptie;
// mag NIET addQuote() zijn hier
// anders wordt het maar 1 keer uitgevoerd, namelijk na het laden van de html pagina
// en het moet telkens wanneer er op de button wordt gedrukt uitgevoerd worden

let getNewQuoteRequest = new XMLHttpRequest();
// 0
// The request is not initialized.
// After you have created the XMLHttpRequest object, but before you have called the open() method.
let newQuoteRequest = new XMLHttpRequest();

function getNewQuote () {
	// getNewQuoteRequest.open("GET", "ScoreServlet", true);
	// 1
	// The request has been set up.
	// After you have called the open() method, but before you have called send().
	// getNewQuoteRequest.onreadystatechange = showQuotes;
	// mag NIET showQuotes() zijn
	// want dat wordt het maar 1 keer uitgevoerd
	// en het moet telkens wanneer de readystate van het xhr veranderd worden uitgevoerd
	// getNewQuoteRequest.send();
	// 2
	// The request has been sent.
	// After you have called send().
	fetch("/ScoreServlet?command=test").then(response=>response.json()).then(data=> showQuotes(data))
}

// 3
// The request is in process.
// After the browser has established a communication with the server, but before the server has completed the response.

// 4
// The request is completed.
// After the request has been completed, and the response data has been completely received from the server.

// callback function
function showQuotes (quote) {
	// if (getNewQuoteRequest.readyState == 4) {
	// 	if (getNewQuoteRequest.status == 200) {
	// 		let quote = JSON.parse(getNewQuoteRequest.responseText);

			let quoteDiv = document.getElementById("personeelslid1");
			let quoteDiv2 = document.getElementById("personeelslid2");
			let quoteDiv3 = document.getElementById("personeelslid3");
			let quoteParagraph = quoteDiv.childNodes[0];
			let quoteParagraph2 = quoteDiv2.childNodes[0];
			let quoteParagraph3 = quoteDiv3.childNodes[0];
			let quoteText = document.createTextNode(quote[0].text + " " + quote[0].score); // kan ook quote["text"]
			let quoteText2 = document.createTextNode(quote[1].text + " " + quote[1].score);
			let quoteText3 = document.createTextNode(quote[2].text+ " " + quote[2].score);
			// console.log(quote[1].text)
			// console.log(quote[2].text)

			if (quoteParagraph == null) {
				quoteParagraph = document.createElement('p');
				quoteParagraph.appendChild(quoteText);
				quoteParagraph2 = document.createElement('p');
				quoteParagraph2.appendChild(quoteText2);
				quoteParagraph3 = document.createElement('p');
				quoteParagraph3.appendChild(quoteText3);
				quoteDiv.appendChild(quoteParagraph);
				quoteDiv2.appendChild(quoteParagraph2);
				quoteDiv3.appendChild(quoteParagraph3);

			} else {
				quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
				quoteParagraph.appendChild(quoteText);
				quoteParagraph2.removeChild(quoteParagraph2.childNodes[0]);
				quoteParagraph2.appendChild(quoteText2);
				quoteParagraph3.removeChild(quoteParagraph3.childNodes[0]);
				quoteParagraph3.appendChild(quoteText3);
			}
			setTimeout(getNewQuote, 1000);
	// 	}
	// }
}

// function addQuote () {
// 	let quoteText = document.getElementById("quotetext").value;
// 	let quoteScore = document.getElementById("quotescore").value;
// 	// encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
// 	let information = "quote=" + encodeURIComponent(quoteText) + "," + encodeURIComponent(quoteScore);
// 	//&
// 	console.log(information);
// 	newQuoteRequest.open("POST", "ScoreServlet", true);
// 	// belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
// 	// protocol header information
// 	newQuoteRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
// 	newQuoteRequest.send(information);
// }
async function addConsumptie() {

	let naam = document.getElementById("quotetext").value;
	let score = document.getElementById("quotescore").value;

	let sNaam = encodeURIComponent(naam);
	let sScore = encodeURIComponent(score);

	const options = {
		method: 'POST',
		body: JSON.stringify({
			text: sNaam,
			score: sScore
		}),
		headers: {'Content-type': 'application/json; charset=UTF-8'}
	}
	let test = await fetch("/ScoreServlet", options);
	location.reload()
}
