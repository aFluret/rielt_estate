<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="finalkursproject.entity.Service" scope="page" id="service"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.date" var="date"/>
<fmt:message bundle="${loc}" key="local.word.add_to_basket" var="add_to_basket"/>

<style>
    <%@include file="/front/css/servicelist.css" %>
</style>

<div class="registration-container">
    <div class="row">

        <c:choose>
            <c:when test="${services!=null}">
                <c:forEach var="service" items="${services}">
                    <form action="/rielt-torg.by/add_service_basket?idService=${service.idService}" method="post">
                        <div class="col-md-4 col-sm-6">
                            <div class="box">
                                <div class="pic">
                                    <img src="/front/image/service/${service.imagePath}"
                                         alt="" style="height: 380px"/>
                                </div>
                                <div class="over-layer">
                                    <h4 class="post">

                                                <a href="#">${service.nameRu}</a>

                                        <small>${cost}: ${service.cost} BYN</small>

                                        <%--<small>${weight}: ${service.weight} g</small>--%>
                                        <select>
                                            <option>Аренда</option>
                                            <option>Покупка</option>
                                        </select>
                                        <%--<small><input type="number" name="quantity" value="0" min="0" max="100" class="input-number"></small>--%>

                                        <small><input type="date" id="date" name="date"
                                               placeholder=${date} ></small>

                                        <small><button type="submit" class="btn btn-default">${add_to_basket}</button></small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:when>
        </c:choose>


    </div><!-- /.row -->
</div>
<!-- /.container -->
