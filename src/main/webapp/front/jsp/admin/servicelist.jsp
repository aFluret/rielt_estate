<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="finalkursproject.entity.Service" scope="page" id="service"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.delete_service" var="delete_service"/>
<fmt:message bundle="${loc}" key="local.word.add_service_button" var="add_service_button"/>
<fmt:message bundle="${loc}" key="local.word.g" var="g"/>
<fmt:message bundle="${loc}" key="local.word.change_service_word" var="change_service_word"/>
<script>
    <%@include file="/front/js/form.js" %>
</script>
<style>
    <%@include file="/front/css/servicelist.css" %>
</style>
<div class="container">
    <button class="btn btn-default" id="add-button"><a id="signup">${add_service_button}</a></button>
    <div class="row">

        <c:choose>
            <c:when test="${services!=null}">
                <c:forEach var="service" items="${services}">
                    <form action="/rielt-torg.by/delete_service?idService=${service.idService}" method="post">
                        <div class="col-md-4 col-sm-6">
                            <div class="box">
                                <div class="pic">
                                    <img src="/front/image/service/${service.imagePath}"
                                         alt="" style="height: 380px"/>
                                </div>
                                <div class="over-layer">
                                    <h4 class="post">
                                        <c:choose>
                                            <c:when test="${locale eq 'ru'}">
                                                <a href="#">${service.nameRu}</a>
                                            </c:when>
                                            <c:when test="${locale eq 'en'}">
                                                <a href="#">${service.nameEn}</a>
                                            </c:when>
                                        </c:choose>
                                        <small>${cost}: ${service.cost} BYN</small>

                                            <%--<div id="flex-btn-service" style="width: 480px;">--%>
                                        <small>
                                            <button type="button" class="btn btn-default" onclick="showEditService(${service.idService})">${change_service_word}</button>
                                        </small>
                                        <small>
                                            <button type="submit" class="btn btn-default">${delete_service}</button>
                                        </small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:when>
        </c:choose>


    </div>
    <!-- /.row -->
</div>
<!-- /.container -->
