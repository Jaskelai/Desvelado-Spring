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
        $('.post-module').hover(function () {
            $(this).find('.description').stop().animate({
                height: "toggle",
                opacity: "toggle"
            }, 300);
        });
    });
});