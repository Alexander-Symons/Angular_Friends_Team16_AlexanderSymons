document.getElementById("body").onload = openSocket;

let webSocket;

function openSocket(){
    webSocket = new WebSocket("ws://localhost:8080/boodschappen");

    webSocket.onopen = function(event){
        writeResponse("Connection opened")
    };

    webSocket.onmessage = function(event){
        writeResponse(event.data);
    };

    webSocket.onclose = function(event){
        writeResponse("Connection closed");
    };
}

function send(){
    let gezinslid = document.getElementById("gezinslid").value;
    let item = document.getElementById("item").value;
    let aantal = document.getElementById("aantal").value;

    webSocket.send(gezinslid + " heeft een item toegevoed: " + item + " " + aantal);
}

function closeSocket(){
    webSocket.close();
}

function writeResponse(text){
    console.log(text)
    if(text.includes("bert")){
        comment = document.getElementById("bert");
    }else if(text.includes("jan")){
        comment = document.getElementById("jan");
    }else if(text.includes("marie")){
        comment = document.getElementById("marie");
    }else if(text.includes("jozef")){
        comment = document.getElementById("jozef");
    }else{
        comment = document.getElementById("open");
    }
    console.log(comment);
    comment.innerHTML += "<br/>" + text ;
}