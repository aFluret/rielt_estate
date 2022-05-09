<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.word.login" var="login"/>
<fmt:message bundle="${loc}" key="local.word.password" var="password"/>
<fmt:message bundle="${loc}" key="local.word.enter" var="enter"/>
<fmt:message bundle="${loc}" key="local.word.administrator" var="administrator"/>
<fmt:message bundle="${loc}" key="local.word.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.word.name" var="name"/>
<fmt:message bundle="${loc}" key="local.word.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.word.email" var="email"/>
<fmt:message bundle="${loc}" key="local.word.registrate" var="registrate"/>
<fmt:message bundle="${loc}" key="local.sentence.login_mistake" var="login_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.password_mistake" var="password_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.name_mistake" var="name_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.surname_mistake" var="surname_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.email_mistake" var="email_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.adding_service" var="adding_service"/>
<fmt:message bundle="${loc}" key="local.word.boldor" var="large"/>
<fmt:message bundle="${loc}" key="local.word.nebmeb" var="small"/>
<fmt:message bundle="${loc}" key="local.word.srmeb" var="pedicure"/>
<fmt:message bundle="${loc}" key="local.word.peregovor" var="meetingrooms"/>
<fmt:message bundle="${loc}" key="local.word.eng_name" var="eng_name"/>
<fmt:message bundle="${loc}" key="local.word.rus_name" var="rus_name"/>
<fmt:message bundle="${loc}" key="local.word.service_name_mistake" var="service_name_mistake"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.cost_mistake" var="cost_mistake"/>
<fmt:message bundle="${loc}" key="local.word.add_service_button" var="add_service_button"/>
<fmt:message bundle="${loc}" key="local.word.choose_image" var="choose_image"/>
<fmt:message bundle="${loc}" key="local.word.adding_admin" var="adding_admin"/>
<fmt:message bundle="${loc}" key="local.word.repassword" var="repassword"/>
<fmt:message bundle="${loc}" key="local.word.repassword_mistake" var="repassword_mistake"/>
<fmt:message bundle="${loc}" key="local.word.changing_password" var="changing_password"/>
<fmt:message bundle="${loc}" key="local.word.password_old" var="password_old"/>
<fmt:message bundle="${loc}" key="local.word.password_new" var="password_new"/>
<fmt:message bundle="${loc}" key="local.word.adding_account" var="adding_account"/>
<fmt:message bundle="${loc}" key="local.word.account_number_mistake" var="account_number_mistake"/>
<fmt:message bundle="${loc}" key="local.word.changing_service" var="changing_service"/>
<fmt:message bundle="${loc}" key="local.word.change_service_word" var="change_service_word"/>

<fmt:message bundle="${loc}" key="local.word.reviewed_registration" var="reviewed_registration"/>
<fmt:message bundle="${loc}" key="local.word.your_review" var="your_review"/>
<fmt:message bundle="${loc}" key="local.word.review_mistake" var="review_mistake"/>
<fmt:message bundle="${loc}" key="local.word.make_review_word" var="make_review_word"/>


<head>
    <meta charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <style>
        <%@include file="/front/css/form.css" %>
        <%@include file="/front/css/review.css" %>
    </style>
    <script>
        <%@include file="/front/js/form.js" %>
    </script>
</head>


<div class="modal" class="modal fade" class="cd-user-modal" id="sign_in" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="sign-in-form" class="form-horizontal form-horizontal-log" method="post"
                              action="/rielt-torg.by/sign_in">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${authorization}</span>

                            <div class="form-group">
                                <input type="text" class="form-control " id="inputLogin"
                                       placeholder=${login} name="login_in">
                                <span class="cd-error-message" id="login-span">${login_mistake}</span>

                            </div>
                            <div class="form-group help">
                                <input type="password" class="form-control has-bregistration" id="inputPassword"
                                       placeholder=${password} name="password_in">
                                <span class="cd-error-message" id="password-span">${password_mistake}</span>

                            </div>
                            <div class="form-group">
                                <button type="submit" id="signin-button" class="btn btn-info">${enter}</button>
                            </div>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


<div class="modal" class="modal fade" class="cd-user-modal" id="sign_up" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="sign-up-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/sign_up">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${registration}</span>

                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="name"
                                       placeholder=${name} name="name">
                                <span class="cd-error-message" id="name-up-span">${name_mistake}</span>

                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="surname"
                                       placeholder=${surname} name="surname">
                                <span class="cd-error-message" id="surname-up-span">${surname_mistake}</span>

                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control has-bregistration" id="email"
                                       placeholder=${email} name="email">
                                <span class="cd-error-message" id="email-up-span">${email_mistake}</span>

                            </div>


                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="login"
                                       placeholder=${login} name="login_up">
                                <span class="cd-error-message" id="login-up-span">${login_mistake}</span>

                            </div>
                            <div class="form-group help">
                                <input type="password" class="form-control has-bregistration" id="password"
                                       placeholder=${password} name="password_up">
                                <span class="cd-error-message" id="password-up-span">${password_mistake}</span>

                            </div>

                            <button type="submit" id="signup-button" class="btn btn-default">${registrate}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>

<div class="modal" class="modal fade" class="cd-user-modal" id="add_service_dialog" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="add-service-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/add_service_action">

                            <button type="button" data-dismiss="modal" class="close">&times;</button>

                            <span class="heading">${adding_service}</span>

                            <div class="form-group">

                                <label for="service-type">Выбор продажи/аренды</label>
                                <select id="service-type" name="service_type">
                                    <option value="large">${large}</option>
                                    <option value="small">${small}</option>
                                    <option value="medium">${medium}</option>
                                    <option value="meetingrooms">${meetingrooms}</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <input type="text" name="nameRu" class="form-control has-bregistration" id="nameRu"
                                       placeholder=${rus_name}>
                                <span class="cd-error-message" id="name-ru">${service_name_mistake}</span>

                            </div>


                            <div class="form-group">
                                <input type="text" name="cost" class="form-control has-bregistration" id="cost"
                                       placeholder=${cost}>
                                <span class="cd-error-message" id="cost-span">${cost_mistake}</span>

                            </div>

                            <div class="form-group">
                                <p>
                                    <label for="imageName" class="control-label" style="float: left">${choose_image}</label>
                                </p>

                                <input type="file" accept=".png, .jpg, .jpeg" style="float: right" name="imageName"
                                       id="imageName">

                                <span class="cd-error-message" id="image-span">${image_mistake}</span>

                            </div>

                            <button type="submit" id="add-service-button"
                                    class="btn btn-default">${add_service_button}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


<div class="modal" class="modal fade" class="cd-user-modal" id="add_admin" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="add-admin-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/add_admin">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${adding_admin}</span>


                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="loginAdmin"
                                       placeholder=${login} name="login-admin">
                                <span class="cd-error-message" id="login-admin">${login_mistake}</span>
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control has-bregistration" id="passwordAdmin"
                                       placeholder=${password} name="password-admin">
                                <span class="cd-error-message" id="password-admin">${password_mistake}</span>
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control has-bregistration" id="repasswordAdmin"
                                       placeholder=${repassword} name="repassword-admin">
                                <span class="cd-error-message" id="repassword-admin">${password_mistake}</span>
                                <span class="cd-error-message" id="repassword-confirm">${repassword_mistake}</span>

                            </div>

                            <button type="submit" id="button"
                                    class="btn btn-default">${add_admin_word}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>

<div class="modal" class="modal fade" class="cd-user-modal" id="change_password" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="change-password-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/change_password">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${changing_password}</span>


                            <div class="form-group">
                                <input type="password" class="form-control has-bregistration" id="passwordOld"
                                       placeholder=${password_old} name="password-old">
                                <span class="cd-error-message" id="password-old">${password_mistake}</span>
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control has-bregistration" id="passwordNew"
                                       placeholder=${password_new} name="password-new">
                                <span class="cd-error-message" id="password-new">${password_mistake}</span>

                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control has-bregistration" id="repasswordNew"
                                       placeholder=${repassword} name="repassword-new">
                                <span class="cd-error-message" id="repassword-new">${password_mistake}</span>
                                <span class="cd-error-message" id="repassword-new-confirm">${repassword_mistake}</span>

                            </div>

                            <button type="submit" id="button-password"
                                    class="btn btn-default">${change_password_word}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>

<div class="modal" class="modal fade" class="cd-user-modal" id="add_account" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="add-account-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/add_account">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${adding_account}</span>


                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="accountNumber"
                                       placeholder=${account_number} name="account-number">
                                <span class="cd-error-message" id="account-number">${account_number_mistake}</span>
                            </div>

                            <button type="submit"
                                    class="btn btn-default">${add_account_btn}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


<div class="modal" class="modal fade" class="cd-user-modal" id="change_service" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="change-service-form" class="form-horizontal form-horizontal-reg" method="post"
                              action="/rielt-torg.by/edit_service?idService=${idService}">

                            <button type="button" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${changing_service}</span>

                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="id_service"
                                       placeholder=${eng_name} name="id_service" readonly>
                            </div>

                            <div class="form-group">

                                <label for="service-type-change">Выберите тип квартиры</label>
                                <select id="service-type-change" name="service_type">
                                    <option value="large">${large}</option>
                                    <option value="small">${small}</option>
                                    <option value="medium">${medium}</option>
                                    <option value="meetingrooms">${meetingrooms}</option>

                                </select>
                            </div>


                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="nameRuch"
                                       placeholder=${rus_name} name="nameRu" value="${service.nameRu}">
                                <span class="cd-error-message" id="name-ru-ch">${service_name_mistake}</span>

                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control has-bregistration" id="costch"
                                       placeholder=${cost} name="cost" value="${service.cost}">
                                <span class="cd-error-message" id="cost-span-ch">${cost_mistake}</span>

                            </div>

                            <input id="changeserviceid" type="text" name="id" style="visibility: hidden">

                            <div class="form-group">
                                <p>
                                    <label for="imagech" class="control-label" style="float: left">${choose_image}</label>
                                </p>
                                <input type="file" accept=".png, .jpg, .jpeg" value="${service.imagePath}"
                                       style="float: right" name="image" id="imagech">
                                <span class="cd-error-message" id="image-span-ch">${image_mistake}</span>

                            </div>

                            <button type="submit" id="change-service-button"
                                    class="btn btn-default">${change_service_word}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


<div class="modal" class="modal fade" class="cd-user-modal" id="add_review" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="add-review-form" class="form-horizontal form-horizontal-log" method="post"
                              action="/rielt-torg.by/add_review">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${reviewed_registration}</span>

                            <div class="form-group">
                                <textarea rows="10" cols="45" class="form-control has-bregistration" id="inputReview"
                                          placeholder=${your_review} name="review"
                                          style="height: 150px;padding-top: 6px"></textarea>
                                <span class="cd-error-message" id="review-span">${review_mistake}</span>

                            </div>
                            <div class="form-group help">
                                <div class="stars">
                                    <input class="star-rating__input" id="star-rating-5" type="radio" name="rating5"
                                           value="5">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5"
                                           title="5 out of 5 stars"></label>
                                    <input class="star-rating__input" id="star-rating-4" type="radio" name="rating4"
                                           value="4">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4"
                                           title="4 out of 5 stars"></label>
                                    <input class="star-rating__input" id="star-rating-3" type="radio" name="rating3"
                                           value="3">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3"
                                           title="3 out of 5 stars"></label>
                                    <input class="star-rating__input" id="star-rating-2" type="radio" name="rating2"
                                           value="2">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2"
                                           title="2 out of 5 stars"></label>
                                    <input class="star-rating__input" id="star-rating-1" type="radio" name="rating1"
                                           value="1">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1"
                                           title="1 out of 5 stars"></label></div>
                            </div>
                            <button type="submit" class="btn btn-default">${make_review_word}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


