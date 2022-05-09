<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.sentence.main_text_first" var="main_text_first"/>
<fmt:message bundle="${loc}" key="local.sentence.main_text_second" var="main_text_second"/>

<head>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
    </script>
</head>
<div>
<img  src="<c:url value="/front/image/fon_2.jpg"/> " alt=""/>
</div>
