<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account replenishment</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet'
          type='text/css'>
    <script src="<c:url value="/resources/js/lib/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/js/onstart.js"/>"></script>
    <script type="text/javascript">
        var $applicationRoot = '/';
        var $host = document.location.protocol + '//' + document.location.host;
        var $hostRoot = document.location.protocol + '//' + document.location.host + $applicationRoot;
    </script>
</head>
<body class="body">
<div class="container">
    <a href="/signup" class="ref">
        <div class="headdiv">РЕГИСТРАЦИЯ</div>
    </a>
    <h3 class="headtextreg"> </h3>
    <form:form action="/signup" method="post" commandName="userReg">
        <div class="forminputreg">
            <label for="email">Логин:</label>
            <input id="name" name="email" type="text" size="29" path="email" value="${email}">
            <form:errors path="email" cssClass="error"/>
            <c:if test="${messageErrorEmail != ''}">
                <p class="error">${messageErrorEmail}</p>
            </c:if>
        </div>
        <div class="forminputreg">
            <label for="password">Пароль:</label>
            <input id="password" name="password" type="password" size="29" value="${password}">
            <form:errors path="password" cssClass="error"/>
            <c:if test="${messageError != ''}">
                <p class="error">${messageError}</p>
            </c:if>
        </div>
        <div class="forminputreg">
            <label for="passwordrepeat">Повтор пароля:</label>
            <input id="passwordrepeat" name="passwordRepeat" type="password" size="29" value="${passwordRepeat}">
            <form:errors path="passwordRepeat" cssClass="error"/>
            <c:if test="${messageSuccess != ''}">
                <p class="success">${messageSuccess}</p>
            </c:if>
        </div>
        <div class="buttondivreg">
            <a href="/userpage">Войти</a>
            <button class="buttonclass" type="submit">Сохранить</button>
        </div>
    </form:form>
    <div class="footer">©The Account Replenishment System. 2016</div>
</div>
</body>
</html>