
$(document).ready(function(){
    $("p").on({
        mouseenter: function(){
            $(this).css("color", "red");
        },

        mouseleave: function (){
            $(this).css("color", "blue");
        },

        mousedown: function (){
            $(this).css("color", "yellow");
        }
    });
});