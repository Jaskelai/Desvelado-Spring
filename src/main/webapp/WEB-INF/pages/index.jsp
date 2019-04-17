<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Desvelado</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/home_main.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/footer.css"/>">
    <link rel="style sheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/assets/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/assets/scripts/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="pageContainer">
    <div id="navbar">
        <jsp:include page="../parts/navbar.jsp"/>
    </div>
    <header class="masthead text-white text-center background-custom">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-xl-9 mx-auto">
                    <h1 class="mb-5">Welcome to Desvelado!</h1>
                </div>
            </div>
        </div>
    </header>
    <div id="footer">
        <jsp:include page="../parts/footer.jsp"/>
    </div>
</div>
</body>
</html>