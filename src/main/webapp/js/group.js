// version of getNewQuote with plain old JavaScript code only
var xHRObject = new XMLHttpRequest();

async function send(groupname) {
    window.location.replace("/groupchat.jsp?groupname="+groupname);
}