jQuery(function () {
    $("#form").on('submit', function (e) {
        e.preventDefault();
        var email = $('#emailField').val();
        var password = $('#passField').val();
        var rememberme = $('#remembermeField').is(':checked');
        var dataFields = {
            "email": email,
            "password": password,
            "rememberme": rememberme
        };
        $.ajax({
            type: 'Post',
            url: 'login',
            data: dataFields,
            success: function (result) {
                if (result.fieldError != null) {
                    $('#error').text(result.fieldError);
                }
                if (result.url != null) {
                    email.value = '';
                    password.value = '';
                    window.location.href = result.url;
                }
            },
            error: function (result) {
                alert("Something went wrong with server...");
            }
        })
    });
});