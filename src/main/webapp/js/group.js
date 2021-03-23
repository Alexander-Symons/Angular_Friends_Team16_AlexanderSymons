// version of getNewQuote with plain old JavaScript code only
var xHRObject = new XMLHttpRequest();

async function send(test) {
    window.location.replace("/groupchat.jsp?groupname="+test);
}