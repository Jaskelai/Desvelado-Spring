<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/date.tld" prefix="m" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Videos</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/videos_main.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/assets/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/assets/scripts/bootstrap.min.js"/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.min.js"></script>
    <script src="<c:url value ="/assets/scripts/videoCatalog.js"/>"></script>
    <sec:csrfMetaTags/>
</head>
<body>
<div id="navbar">
    <jsp:include page="../parts/navbar.jsp"/>
</div>
<main class="profile background-custom">
    <div class="row top-buffer">
        <c:forEach items="${videos}" var="video">
            <inputTag:video id="${video.youtubeId}" headerVideo="${video.header}"
                            description="${video.description}" datePost="${video.date.time}" like="${video.liked}"
                            likesCount="${video.likes}" username="${video.user.username}"/>
        </c:forEach>
    </div>
    <div></div>
    <div class="modal fade" id="modalUpload" tabindex="-1" role="dialog" aria-labelledby="modalUpload"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-custom" role="document">
            <div class="modal-content">
                <div class="modal-headerVideo modal-headerVideo-custom">
                    <h4 class="modal-title">Add a video from youtube</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form method="post" modelAttribute="video">
                    <div class="form-custom">
                        <div class="input-custom">
                            <label>Youtube link:</label>
                            <input type="text" name="youtubeLink" id="linkInput"
                                   class="form-control form-control-custom"
                                   maxlength="50">
                        </div>
                        <div class="input-custom">
                            <label>Header:</label>
                            <input type="text" name="header" id="headerInput" class="form-control form-control-custom"
                                   maxlength="30">
                        </div>
                        <div class="input-custom">
                            <label>Description:</label>
                            <textarea id="descriptionInput" name="description" class="form-control from-control-custom"
                                      maxlength="500"></textarea>
                        </div>
                    </div>
                    <div id="error"></div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-unique" id="btnUpload">Upload</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <security:authorize access="isAuthenticated()">
        <a class="btn-float" href="" data-toggle="modal" data-target="#modalUpload"><i
                class="fas fa-plus fa-3x iconAdd"></i></a>
    </security:authorize>
</main>
<div id="footer">
    <jsp:include page="../parts/footer.jsp"/>
</div>
</body>
</html>
