<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
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
    <%--<link rel="shortcut icon" type="image/png" href="<c:url value="/resources/img/favicon.png"/>">--%>
</head>
<body class="body">
<div class="container">
    <a href="/" class="ref">
        <div class="headdiv">ДОБРО ПОЖАЛОВАТЬ!</div>
    </a>
    <h3 class="headtext">Для начала работы войдите <br>в систему под своим логином:</h3>
    <form action="/j_spring_security_check" method="post">
        <div class="forminput">
            <label for="name">Логин:</label>
            <input id="name" name="j_username" type="text" required size="28">
        </div>
        <div class="forminput">
            <label for="password">Пароль:</label>
            <input id="password" name="j_password" type="password" required size="28">
        </div>
        <div class="forminput">
            <c:if test="${param.error!=null}">
                <div class="error">Неправильный логин или пароль</div>
            </c:if>
        </div>
        <div class="buttondiv">
            <a href="signup">Зарегистрироваться</a>
            <button class="buttonclass" type="submit">Войти</button>
        </div>
    </form>
    <div class="footer">©The Account Replenishment System. 2016</div>
</div>
</body>
</html>