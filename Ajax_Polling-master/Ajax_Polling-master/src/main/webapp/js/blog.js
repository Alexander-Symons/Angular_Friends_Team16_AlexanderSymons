document.getElementById("body").onload = openSocket;

let webSocket;

function openSocket(){
    webSocket = new WebSocket("ws://localhost:8080/blog");

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
    let naam = document.getElementById("gebruikersNaam").value;
    let comment = document.getElementById("comment").value;
    let topic = document.getElementById("topics").value;

    webSocket.send(naam + " heeft een comment geplaatst op " + topic + ": " + comment);
}

function closeSocket(){
    webSocket.close();
}

function writeResponse(text){
    console.log(text)
    if(text.includes("vaccine")){
        comment = document.getElementById("vaccine");
    }else if(text.includes("coronaCijfers")){
        comment = document.getElementById("coronaCijfers");
    }else if(text.includes("Cars_2")){
        comment = document.getElementById("Cars_2");
    }else if(text.includes("Toy_tory_3")){
        comment = document.getElementById("Toy_tory_3");
    }else{
        comment = document.getElementById("open");
    }
    console.log(comment);
    comment.innerHTML += "<br/>" + text ;
}