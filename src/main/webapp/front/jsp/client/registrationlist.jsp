<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="finalkursproject.entity.Registration" scope="page" id="registration"/>
<jsp:useBean class="finalkursproject.entity.Service" scope="page" id="service"/>
<jsp:useBean class="finalkursproject.entity.RegistrationService" scope="page" id="registrationService"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.registration_word" var="registration_word"/>
<fmt:message bundle="${loc}" key="local.word.total_cost" var="total_cost"/>
<fmt:message bundle="${loc}" key="local.word.remove_from_basket" var="remove_from_basket"/>
<fmt:message bundle="${loc}" key="local.word.has_been_paid" var="has_been_paid"/>
<fmt:message bundle="${loc}" key="local.word.pay" var="pay"/>
<fmt:message bundle="${loc}" key="local.word.not_exist" var="not_exist"/>
<fmt:message bundle="${loc}" key="local.word.status_word" var="status_word"/>
<fmt:message bundle="${loc}" key="local.word.make_review_word" var="make_review_word"/>

<style>
    <%@include file="/front/css/registrationlist.css" %>
</style>

<div class="registration-container">

    <div class="qa-message-list">

        <c:choose>
            <c:when test="${registrations!=null}">
                <c:forEach var="registration" items="${registrations}">


                    <div style="outline: 2px solid #000000" >
                        <div class="text-center">
                            <div class="message-head clearfix">
                                <form action="/rielt-torg.by/pay_for_registration?idRegistration=${registration.idRegistration}" method="post">
                                    <div class="user-detail">
                                        <h5 class="handle">${registration_word} ${registration.idRegistration} </h5>
                                        <c:choose>
                                            <c:when test="${registration.status eq 'true'}">
                                                <button type="submit"
                                                        class="btn btn-success">${pay}</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button"
                                                        class="btn btn-success" onclick="viewAddReview(${registration.idRegistration})">${make_review_word}</button>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="post-meta">
                                            <div class="asker-meta">
                                                <span class="qa-message-what"></span>
                                                <span class="qa-message-when">
 <span class="qa-message-when-data">${registration.date}</span>

 </span>
                                                <span class="qa-message-who">
 <span class="qa-message-who-pad">${total_cost} </span>
 <span class="qa-message-who-data">${registration.totalCost}</span>
 </span><br>
                                                <c:if test="${registration.status eq 'false'}">
                                                    <span class="qa-message-who-pad">${status_word}:${has_been_paid}</span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="qa-message-content">
                                <c:forEach var="registrationService" items="${registration_services}">

                                    <c:if test="${registrationService.idRegistration==registration.idRegistration}">
                                        <c:forEach var="service" items="${services}">
                                            <c:if test="${registrationService.idService==service.idService}">
                                                <form action="/rielt-torg.by/remove_service_basket?idService=${service.idService}&idRegistration=${registration.idRegistration}&quantity=${registrationService.quantity}"
                                                      method="post">

                                                    <p>
                                                        <c:choose>
                                                            <c:when test="${locale eq 'ru'}">
                                                                ${service.nameRu} (${service.cost} BYN )
                                                            </c:when>
                                                            <c:when test="${locale eq 'en'}">
                                                                ${service.nameEn} (${service.cost} BYN )
                                                            </c:when>
                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${service.exist eq 'false'}">
                                                                <small style="float: right">${not_exist}</small>
                                                            </c:when>
                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${registration.status eq 'true'}">
                                                                <button type="submit"
                                                                        class="btn btn-warning">${remove_from_basket}</button>
                                                            </c:when>
                                                        </c:choose>
                                                    </p>
                                                </form>
                                            </c:if>
                                        </c:forEach>

                                    </c:if>


                                </c:forEach>
                            </div>
                        </div>
                    </div>


                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->

</div>
<!-- ./container -->
