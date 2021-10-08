function Notify() {}

Notify.updateRequest = function () 
{
    $.ajax({
        type: "POST",
        data: { "ajaxRequest" : "getUpdateAmm" },
        dataType: "html",
        success: function(risposta){
            $("#notify_amm").html(risposta);
        }
    });
};

Notify.updateNews = function ()
{
    $.ajax({
        type: "POST",
        data: { "ajaxRequest" : "getUpdateNews" },
        dataType: "html",
        success: function(risposta){
            $("#notify_news").html(risposta);
        }
    });
};

Notify.updateFriendRequest = function () {
    $.ajax({
        type: "POST",
        data: { "ajaxRequest" : "getUpdateFriend" },
        dataType: "html",
        success: function(risposta){
            $("#notify_friend").html(risposta);
        }
    });
}