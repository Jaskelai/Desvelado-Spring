jQuery(function () {
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
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var dataFields = "isLiked=" + isLiked + "&idLink=" + idLink + "&info=" + isLiked.toString() + "&_csrf=" + csrfToken;
        $.ajax({
            type: "POST",
            url: "like",
            data: dataFields,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, csrfToken);
            },
            success: function (result) {
            }
            ,
            error: function (result) {
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