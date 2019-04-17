<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<form:form method="POST" modelAttribute="usersEleven">

    <form:label path="name">Name:</form:label>
    <form:input path="name"/>
    <form:errors path="name"/><br><br>

    <form:label path="email">Email:</form:label>
    <form:input path="email"/>
    <form:errors path="email"/><br><br>

    <form:label path="languages">Langs:</form:label>
    <form:select path="languages" size="3" multiple="true">
        <form:option value="Java"/>
        <form:option value="C++"/>
        <form:option value="JS"/>
    </form:select>
    <form:errors path="languages"/><br><br>

    <input type="submit" value="Submit"/>
</form:form><br><br>
</body>
</html>
