<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<nav class="navbar navbar-expand-md navbar-dark">
    <a class="navbar-brand" href='${s:mvcUrl("BC#home").build()}'>Desvelado</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href='${s:mvcUrl("BC#home").build()}'>Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='${s:mvcUrl("BC#home").build()}'>Videos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" id="btnAbout" href="#About">About</a>
            </li>
        </ul>
        <ul class="navbar-nav">
        </ul>
    </div>
</nav>
<div class="modal fade" id="About" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">About</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <h3>Welcome to Desvelado</h3>
                <p>We all know the feeling of insomnia. Your body wants to sleep but your mind is still awake and
                    active. So how can we steady up our mind.</p>
                <p>Desvelado is a parody video platform where you can find the most silent and sleepy content selection
                    to relax your brain and easily fall asleep.</p>
                <p>Just click on the "Video" button on top, and you will receive a list of videos that will help you
                    fall asleep.</p>
                <p>Registered users will be able to add videos to favorites, by liking them, and thereby promote this
                    video to the top.</p>
                <p>Taking siesta to the next level.</p>

            </div>
        </div>
    </div>
</div>