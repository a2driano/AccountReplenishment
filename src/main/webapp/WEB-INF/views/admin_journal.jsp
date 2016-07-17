<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="lastUrl" value="/admin_journal/${lastPage}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account replenishment</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/admin.css"/>"/>
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
    <a href="/admin_journal" class="ref">
        <div class="headdiv">ЖУРНАЛ ПОПОЛНЕНИЙ БАЛАНСА</div>
    </a>

    <div class="info">
        <a href="/admin">Управление балансами</a>
    </div>
    <div class="info">
        <form class="infoinside" action="/admin_journal/${currentPage}" method="get">
            <label for="dateStartStr">Дата с</label>
            <input id="dateStartStr" name="dateStartStr" type="text" size="10" value="${searchValueStart}">
            <label for="dateEndStr">по</label>
            <input id="dateEndStr" name="dateEndStr" type="text" size="10" value="${searchValueEnd}">
            <button class="buttonclass" type="submit">Поиск</button>
        </form>
        <div class="infoinsiderigth">
            <c:if test="${currentPage > 1}">
                <a class="prev"
                   href="/admin_journal/${currentPage-1}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">Prev</a>
            </c:if>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl" value="/admin_journal/${i}"/>
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <a class="active"
                           href="${pageUrl}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">
                            <c:out value="${i}"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageUrl}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">
                            <c:out value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < lastPage}">
                <a class="next"
                   href="/admin_journal/${currentPage+1}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">Next</a>
            </c:if>
        </div>
    </div>
    <div class="tablehead_journal">
        <table>
            <thead>
            <tr class="table_journal">
                <td id="email_admin">Администратор</td>
                <td id="email_journal">Пользователь</td>
                <td id="date_journal">Дата пополнения</td>
                <td id="balance_journal">Сумма пополнения</td>
            </tr>
            </thead>
            <tbody class="tableinfo_journal">
            <c:choose>
                <c:when test="${message!=null}">
                    <h4 class="empty">${message}</h4>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${journal}" var="journal" varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
                            <td id="emailbody_journal" class="tablebody" index=${journal.getIdJournal()}>
                                    ${journal.getAdmin().getEmail()}</td>
                            <td id="emailbody_journal_user" class="tablebody" index=${journal.getUser().getIdUser()}>
                                    ${journal.getUser().getEmail()}</td>
                            <td id="datebody" class="tablebody"><fmt:formatDate value="${journal.getDateJournal()}"
                                                                                pattern="dd/MM/yyyy"/></td>
                            <td id="balancebody" class="tablebody" index="${journal.getSum()}">
                                <c:choose>
                                    <c:when test="${journal.getSum()==0}">0,00 $</c:when>
                                    <c:when test="${journal.getSum()<1}">0<fmt:formatNumber pattern="#,###.00" maxFractionDigits="2"
                                                            value="${journal.getSum()}"/> $</c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber pattern="#,###.00" maxFractionDigits="2"
                                                          value="${journal.getSum()}"/> $
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    <div class="infobottom">
        <div class="infoinsiderigth">
            <c:if test="${currentPage > 1}">
                <a class="prev"
                   href="/admin_journal/${currentPage-1}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">Prev</a>
            </c:if>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl" value="/admin_journal/${i}"/>
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <a class="active"
                           href="${pageUrl}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}"><c:out
                                value="${i}"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageUrl}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}"><c:out
                                value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < lastPage}">
                <a class="next"
                   href="/admin_journal/${currentPage+1}?dateStartStr=${searchValueStart}&dateEndStr=${searchValueEnd}">Next</a>
            </c:if>
        </div>
    </div>
    <a href="/logout">Выйти</a>

    <div class="footer">©The Account Replenishment System. 2016</div>
    <div id="modal_form">
        <div class="modal_header">Пополнение баланса<span id="modal_close">X</span></div>
        <h5 id="name_user"></h5>
        <h6 id="alert_message"></h6>

        <div>
            <label for="sum">Сумма:</label>
            <input id="sum" type="text" required size="20">

            <div class="modal_button_div">
                <button id="modal_button_close" class="modal_button">Отмена</button>
                <button id="modal_button_click" class="modal_button">Пополнить</button>
            </div>
        </div>
    </div>
    <div id="overlay"></div>
</div>
</body>
</html>