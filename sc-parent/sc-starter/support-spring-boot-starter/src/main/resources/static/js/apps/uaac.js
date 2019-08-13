$(function() {
    $.ajax({
        type: "GET",
        url: "uaac/getOnlineUserCount",
        dataType: "json",
        success: function(data){
            $(".onlineNum").html(data);
        }
    });
});