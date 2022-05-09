<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.catalog" var="menu"/>
<fmt:message bundle="${loc}" key="local.word.boldor" var="large"/>
<fmt:message bundle="${loc}" key="local.word.nebmeb" var="small"/>
<fmt:message bundle="${loc}" key="local.word.srmeb" var="medium"/>
<fmt:message bundle="${loc}" key="local.word.peregovor" var="meetingrooms"/>



<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.js"></script>
    <style>
        <%@include file="/front/css/menu.css" %>
    </style>

</head>
<body>

<section class="cd-section">
    <button class="cd-bouncy-nav-trigger">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
</section>

<div class="cd-bouncy-nav-modal">
    <nav>
        <ul class="cd-bouncy-nav">
            <li><a href="/rielt-torg.by/find_by_type?service_type=large">${large}</a></li>
            <li><a href="/rielt-torg.by/find_by_type?service_type=small">${small}</a></li>
            <li><a href="/rielt-torg.by/find_by_type?service_type=medium">${medium}</a></li>
            <li><a href="/rielt-torg.by/find_by_type?service_type=meetingrooms">${meetingrooms}</a></li>

        </ul>
    </nav>
    <a href="#0" class="cd-close">Close modal</a>
</div>

</body>
</html>
