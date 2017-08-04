$(document).ready( () => {
    $("#openCardButton").click(displayCard);
    $("#closeCardButton").click(hideCard);
    

    //$("#test").attr("checked",true);
});

var displayCard = function () {
    $("#newItem").animate({
        top: "80px"
    }, 200 );
}

var hideCard = function () {
$("#newItem").animate({
        top: "-450px"
    }, 200 );
}