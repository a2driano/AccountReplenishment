<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="headdiv">Пользователь: ${user.getEmail()}</div>
    <h3 class="headtextuser">Ваш текущий баланс:</h3>
    <h1 class="headtextuser">
        <c:choose>
            <c:when test="${user.getBalance()==0}">0,00 $</c:when>
            <c:when test="${user.getBalance()<1}">0
                <fmt:formatNumber pattern="#,###.00" maxFractionDigits="2"
                                                                    value="${user.getBalance()}"/> $</c:when>
            <c:otherwise>
                <fmt:formatNumber pattern="#,###.00" maxFractionDigits="2" value="${user.getBalance()}"/> $
            </c:otherwise>
        </c:choose>
    </h1>
    <a href="/logout">Выйти из системы</a>
    <div class="footer">©The Account Replenishment System. 2016</div>
</div>
</body>
</html>