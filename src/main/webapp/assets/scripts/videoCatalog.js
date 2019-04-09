jQuery(function () {
    $("#btnUpload").on('click', function (e) {
        e.preventDefault();
        var link = $('#linkInput').val();
        var header = $('#headerInput').val();
        var description = $('#descriptionInput').val();
        var dataFields = {
            "link": link,
            "header": header,
            "description": description
        };
        $.ajax({
            type: 'Post',
            url: 'addcall',
            data: dataFields,
            success: function (result) {
                if (!result.hasOwnProperty("errorUpload")) {
                    $('#modalUpload').modal('hide');
                    link.value = '';
                    header.value = '';
                    description.value = '';
                } else {
                    $('#error').text(result.errorUpload);
                }
            },
            error: function (result) {
                alert("Something went wrong with server...");
            }
        })
    });
    $(".toggler").on('click', function (e) {
        e.preventDefault();
        var idLink = e.target.id;
        var isLiked = false;
        $(this).toggleClass("fas far");
        var idLike = idLink.concat('likes');
        var likesElement = document.getElementById(idLike);
        var likes = parseInt(likesElement.text, 10);
        if (e.target.classList.contains("fas")) {
            isLiked = true;
            likesElement.innerHTML = '';
            likesElement.innerHTML = likes + 1;
        } else {
            likesElement.innerHTML = '';
            likesElement.innerHTML = likes - 1;
        }
        var dataFields = {
            "isLiked": isLiked,
            "idLink": idLink
        };
        $.ajax({
            type: 'Post',
            url: 'likeserv',
            data: dataFields,
            success: function (result) {


            },
            error: function (result) {
                alert("Something went wrong with server...");
            }
        })
    });
    $(window).on('load', function () {
        $('.post-module').on('hover', function () {
            $(this).find('.description').stop().animate({
                height: "toggle",
                opacity: "toggle"
            }, 300);
        });
    });
});