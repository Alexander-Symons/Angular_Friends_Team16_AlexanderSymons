var audio;

$(document).ready(function (){
    $.ajax({
        url: "ChangeStatus",
        dataType: "text",
        type: "GET",
        success: function (data){
            $("#showStatus").text("current status " + data);
        }
    })

    $("#setStatus").on("click",function (){
        $selected_status = $("input[name='status']:checked").val();
        $.post("ChangeStatus", {status: $selected_status}, function (data){
            $("#showStatus").text("current status " + data);
        })
    })

    $("#addFriend").on("click", function (){
        $naam = $("#naam").val();
        $("#naam").val("");
        $.post("Controller?action=AddFriend", {naam: $naam});
    })
})

$(document).ready(function() {
    $(".sendMessage").on("click", function (){
        $tekst = $(".inputMessage").val();
        $ontvanger = $('.chatbox').attr("id");
        $.ajax({
            type: "POST",
            url: "VerstuurBerichten",
            data: {ontvanger: $ontvanger, tekst: $tekst},
        });
    });
});
var chatLength;
var init = true;
$(document).ready(function(){
    setInterval(function(){
        $.ajax({
            type: "GET",
            url: "VerstuurBerichten?ontvanger=" + $('.chatbox').attr("id"),
            dataType: "json",
            success: function (json) {

                //@Author Arno Piersoul
                //----
                if (chatLength != json.length && init == false){
                    playSound();
                }
                chatLength = json.length;
                init = false;
                //----

                $('.outputMessage').empty();
                $(json).each(function (index, bericht){
                    $test = document.createElement('p')
                    $test.setAttribute("id", "teksinhoud")
                    $test.innerText = bericht.zender.firstName + " : " + bericht.message;
                    $('.outputMessage').append($test);
                })
            },
            error: function () {
                $('.outputMessage').empty();
                $test = document.createElement('p')
                $test.innerText = "Selecteer iemand om mee te chatten"
                $('.outputMessage').append($test);
            }
        });
    },1000);
});

//@Author Robbe Jacobs
$('#showFriendlist').on('click',function(){
    if($("#allFriends").is(":visible")){
        $('#allFriends').hide(1000);
    }else{
        $('#allFriends').show(1000);
    }
});

//@Author Arno Piersoul
$(document).ready(function() {
    audio = document.createElement('audio');
    audio.setAttribute('src', 'audio/notification.wav');
});

//@Author Arno Piersoul
function playSound(){
    audio.play();
}
$(document).ready(function () {
    function poll() {
        $.get({
            url: "/ShowFriends",
            success: function (data) {
                $table = document.getElementById("allFriends");
                if ($table != null) {
                    if ($table.innerHTML != null){
                        $table.innerHTML = "";
                    }
                    $row = document.createElement("tr");
                    $cellH1 = document.createElement("th");
                    $cellH2 = document.createElement("th");
                    $cellH3 = document.createElement("th");

                    $cellH1.innerHTML = "Name";
                    $cellH2.innerHTML = "Status";
                    $cellH3.innerHTML = "Chat";
                    $row.appendChild($cellH1);
                    $row.appendChild($cellH2);
                    $row.appendChild($cellH3);
                    $table.appendChild($row);

                    for (i = 0; i !== data.length; i++) {
                        $row = document.createElement("tr");
                        $cellA = document.createElement("td");
                        $cellB = document.createElement("td");
                        $cellC = document.createElement("td");
                        $button = document.createElement("button");
                        $cellA.innerHTML = data[i].firstName + " " + data[i].lastName;
                        $cellB.innerHTML = data[i].status;
                        $button.innerHTML = "Open chat";
                        $button.setAttribute("class", "buttonChat");
                        $button.setAttribute("id", data[i].firstName);
                        $cellC.appendChild($button);

                        $table.appendChild($row);
                        $row.appendChild($cellA);
                        $row.appendChild($cellB);
                        $row.appendChild($cellC);
                    }
                    $(".buttonChat").on("click", function(){
                        $selected_person = this.id;
                        $('.chatbox').attr("id", $selected_person);
                        $('.outputMessage').innerText = "";
                    })

                }
            },
            timeout: 2500                    // == 10 seconds timeout
        }).always(function () {
            setTimeout(poll, 2500)           // == 30 seconds polling period
        })
    }
        poll()
})