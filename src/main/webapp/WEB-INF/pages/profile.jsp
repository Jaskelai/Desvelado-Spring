<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/profile.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/assets/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/assets/scripts/bootstrap.min.js"/>"></script>
    <script src="<c:url value = "/assets/scripts/profile.js"/>"></script>
    <sec:csrfMetaTags/>
</head>
<body>
<div id="navbar">
    <jsp:include page="../parts/navbar.jsp"/>
</div>
<main class="profile background-custom">
    <h1 class="greetings heading-text">Welcome, <sec:authentication property="principal.username"/></h1>
    <div class="row">
        <div class="column fav-videos" id="leftColumn">
            <div class="center-block">
                <h2 class="column-heading heading-text">Your favourite videos:</h2>
                <c:choose>
                    <c:when test="${fn:length(favVideos) > 0}">
                        <c:forEach items="${favVideos}" var="video">
                            <inputTag:video id="${video.youtubeId}" headerVideo="${video.header}"
                                            description="${video.description}" datePost="${video.date.time}"
                                            like="${video.liked}" likesCount="${video.likes}" username=""/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2 class="empty-videos">No favourite videos</h2>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="column own-videos">
            <div class="center-block">
                <h2 class="column-heading heading-text">Your uploaded videos:</h2>
                <a>${fn:length(ownedVideos)}</a>
                <c:choose>
                    <c:when test="${fn:length(ownedVideos) > 0}">
                        <c:forEach items="${ownedVideos}" var="video">
                            <inputTag:video id="${video.youtubeId}" headerVideo="${video.header}"
                                            description="${video.description}" datePost="${video.date.time}"
                                            like="${video.liked}" likesCount="${video.likes}"
                                            username="${video.user.username}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2 class="empty-videos">No uploaded videos</h2>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>
<div id="footer">
    <jsp:include page="../parts/footer.jsp"/>
</body>
</html>
