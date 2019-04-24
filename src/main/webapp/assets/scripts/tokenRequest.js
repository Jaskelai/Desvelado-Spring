jQuery(function () {
    var token = window.location.hash;
    var dataFields = {
        "token": token
    };
    $.ajax({
        type: "GET",
        url: "shareAjax",
        data: dataFields,
        contentType : "application/json",
        success: function (result) {
            window.location.href = "home";
        }
        ,
        error: function (result) {
        }
    })
});