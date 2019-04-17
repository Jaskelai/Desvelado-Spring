<%@ attribute name="id" required="true" type="java.lang.String" description="Id for video" %>
<%@ attribute name="headerVideo" required="true" type="java.lang.String" description="Header for video" %>
<%@ attribute name="description" required="true" type="java.lang.String" description="Description for video" %>
<%@ attribute name="datePost" required="true" type="java.lang.Long" description="Date of post for video" %>
<%@ attribute name="like" required="true" type="java.lang.Boolean" description="Date of post for video" %>
<%@ attribute name="username" required="true" type="java.lang.String" description="Owner of video" %>
<%@ attribute name="likesCount" required="true" type="java.lang.Integer" description="Count of like" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/jsp/tags/date.tld" prefix="m" %>


<div class="col-12 col-xs-12 col-sm-4 col-md-3 col-lg-3 col-xl-2 video-preview col-sm-offset-1">
    <div class="modal fade modal-custom" tabindex="-1" id="${id}1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <!--Body-->
                <div class="modal-body mb-0 p-0">
                    <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
                        <iframe class="embed-responsive-item"
                                src="https://www.youtube.com/embed/${id}"
                                allowfullscreen></iframe>
                    </div>
                </div>
                <!--Footer-->
                <div class="modal-footer justify-content-center">
                    <span class="mr-4">${description}</span>
                    <button type="button" class="btn btn-outline-primary btn-rounded btn-md ml-4"
                            data-dismiss="modal">Close
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="video-container">
        <div class="post-module">
            <div class="thumbnail">
                <c:choose>
                    <c:when test='${sessionScope.username != null}'>
                        <div class="likeCircle">
                            <c:choose>
                                <c:when test='${like}'>
                                    <a class="likeImg"><i class="fas toggler fa-heart fa-2x" id="${id}"></i></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="likeImg"><i class="far toggler fa-heart fa-2x" id="${id}"></i></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:when>
                </c:choose>
                <img src="https://img.youtube.com/vi/${id}/0.jpg" data-toggle="modal" data-target="#${id}1"/>
            </div>
            <div class="post-content">
                <div class="category">Video</div>
                <h1 class="title">${headerVideo}</h1>
                <p class="description">${description}</p>
                <div class="container">
                    <div class="row row-meta">
                        <div class="post-meta"><span class="timestamp"><i class="fa fa-clock-"></i><m:dateNumber
                                date="${datePost}"/></span></div>
                        <a class="num-likes" id="${id}likes">${likesCount}</a>
                        <a class="heartImg"><i class="fas fa-heart fa-xs"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>