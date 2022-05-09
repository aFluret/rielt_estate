<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="finalkursproject.entity.Service" scope="page" id="service"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>


<div class="container">
    <div class="row">

        <c:choose>
            <c:when test="${services!=null}">
                <c:forEach var="service" items="${services}">

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


                                </h4>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>

    </div><!-- /.row -->
</div><!-- /.container -->
