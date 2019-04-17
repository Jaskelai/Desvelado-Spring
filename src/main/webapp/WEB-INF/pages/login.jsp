<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/registration_login_main.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/footer.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/assets/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/assets/scripts/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/scripts/login.js"/>"></script>
</head>
<body>
<div id="navbar">
    <jsp:include page="../parts/navbar.jsp"/>
</div>
<main class="regForm background-custom">
    <div class="main-w3layouts wrapper">
        <h1>Login:</h1>
        <div class="main-agileinfo">
            <div class="agileits-top">
                <form id="form" method="post">
                    <inputTag:inputTextField textLabel="Enter your email:" idField="emailField" typeField="text"
                                             nameField="email" classField="text" placeholderField="Enter your email"/>
                    <inputTag:inputTextField textLabel="Enter your password:" idField="passField" typeField="password"
                                             nameField="password" classField="text"
                                             placeholderField="Enter your password"/>
                    <inputTag:inputCB nameField="rememberme" idCB="remembermeField" valueField="Remember me?"/>
                    <div id="error"></div>
                    <input type="submit" value="SIGNIN" id="btnSubmit">
                </form>
                <p>Don't have an Account? <a href="<c:url value="/registration"/>"> Register Now!</a></p>
            </div>
        </div>
    </div>
</main>
<div id="footer">
    <jsp:include page="../parts/footer.jsp"/>
</div>
</body>
</html>
