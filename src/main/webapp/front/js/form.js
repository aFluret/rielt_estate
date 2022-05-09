var idRegistration;

$(document).ready(function () {
    $("#signin").click(function () {
        $("#sign_in").modal({backdrop: true});
    });
    $("#signup").click(function () {
        $("#sign_up").modal({backdrop: true});
    });
    $("#changepassword").click(function () {
        $("#change_password").modal({backdrop: true});
    });
    $("#changeservice").click(function () {
        $("#change_service").modal({backdrop: true});
    });
    $("#addaccount").click(function () {
        $("#add_account").modal({backdrop: true});
    });
    $("#addadmin").click(function () {
        $("#add_admin").modal({backdrop: true});
    });
    $("#addService").click(function () {
        $("#add_service_dialog").modal({backdrop: true});
    });

    $('form#sign-in-form').on('click', function (e) {

        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{5,}');
        var $form_modal = $('.cd-user-modal');
        var $form_sign_in = $form_modal.find('#sign_in');
        var $numberCorrectField = 0;

        var password = $('input#inputPassword').val();
        var login = $('input#inputLogin').val();

        if (password.length >= 4 && passwordReg.test(password)) {
            $('input#inputPassword').css('bregistration-color', 'green');
            $('input#inputPassword').removeClass('has-error').next('span#password-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#inputPassword').css('bregistration-color', 'red');
            $('input#inputPassword').addClass('has-error').next('span#password-span').addClass('is-visible');
        }

        if (login.length > 0 && loginReg.test(login)) {
            $('input#inputLogin').css('bregistration-color', 'green');
            $('input#inputLogin').removeClass('has-error').next('span#login-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#inputLogin').css('bregistration-color', 'red');
            $('input#inputLogin').addClass('has-error').next('span#login-span').addClass('is-visible');
        }
        if ($numberCorrectField !== 2) {
            e.preventDefault();
        }
    });

    $('form#sign-up-form').on('click', function (e) {

        var name_surnameReg = new RegExp(`^([\u0410-\u042f]{1}[\u0430-\u044f]+)$|^([A-Z]{1}[a-z]+)$`);
        var emailReg = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{5,}');

        var password = $('input#password').val();
        var login = $('input#login').val();
        var name = $('input#name').val();
        var surname = $('input#surname').val();
        var email = $('input#email').val();

        var $numberCorrectField = 0;

        if (password.length >= 4 && passwordReg.test(password)) {
            $('input#password').css('bregistration-color', 'green');
            $('input#password').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#password').css('bregistration-color', 'red');
            $('input#password').addClass('has-error').next('span').addClass('is-visible');
        }

        if (login.length >= 3 && loginReg.test(login)) {
            $('input#login').css('bregistration-color', 'green');
            $('input#login').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#login').css('bregistration-color', 'red');
            $('input#login').addClass('has-error').next('span').addClass('is-visible');
        }

        if (name.length >= 2 && name_surnameReg.test(name)) {
            $('input#name').css('bregistration-color', 'green');
            $('input#name').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#name').css('bregistration-color', 'red');
            $('input#name').addClass('has-error').next('span').addClass('is-visible');
        }

        if (surname.length >= 2 && name_surnameReg.test(surname)) {
            $('input#surname').css('bregistration-color', 'green');
            $('input#surname').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#surname').css('bregistration-color', 'red');
            $('input#surname').addClass('has-error').next('span').addClass('is-visible');
        }


        if (email.length > 7 && email != '' && emailReg.test(email)) {
            $('input#email').css('bregistration-color', 'green');
            $('input#email').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#email').css('bregistration-color', 'red');
            $('input#email').addClass('has-error').next('span').addClass('is-visible');
        }

        if ($numberCorrectField !== 5) {
            e.preventDefault();
        }
    });

    $("#add-service-button").click(function (e) {

        var rus_nameReg = new RegExp('^([\u0410-\u042f]{1}[\u0430-\u044f\u0410-\u042f-\\s\\-\\`]+)$');

        var costweightReg = new RegExp('^(([0-9]+)(\\.){0,1}([0-9]+))$');

        var rus_name = $('input#nameRu').val();

        var cost = $('input#cost').val();
        var image_name = $('input#imageName').val();

        var $numberCorrectField = 0;


        if (rus_name.length >= 2 && rus_nameReg.test(rus_name)) {
            $('input#nameRu').css('bregistration-color', 'green');
            $('#name-ru').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#nameRu').css('bregistration-color', 'red');
            $('#name-ru').addClass('is-visible');
        }


        if (cost.length >= 0 && costweightReg.test(cost)) {
            $('input#cost').css('bregistration-color', 'green');
            $('#cost-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#cost').css('bregistration-color', 'red');
            $('#cost-span').addClass('is-visible');
        }

        if (image_name.length > 0) {
            $('input#imageName').css('bregistration-color', 'green');
            $('#imageName-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#imageName').css('bregistration-color', 'red');
            $('#imageName-span').addClass('is-visible');
        }

        if ($numberCorrectField !== 3) {
            e.preventDefault();
        }
    });

    $("#change-service-button").click(function (e) {

        var rus_nameReg = new RegExp('^([\u0410-\u042f]{1}[\u0430-\u044f\u0410-\u042f-\\s\\-\\`]+)$');
        var costweightReg = new RegExp('^(([0-9]+)(\\.){0,1}([0-9]+))$');

        var rus_name = $('input#nameRuch').val();
        var cost = $('input#costch').val();
        var image_name = $('input#imagech').val();

        var $numberCorrectField = 0;

        if (rus_name.length >= 2 && rus_nameReg.test(rus_name)) {
            $('input#nameRuch').css('bregistration-color', 'green');
            $('#name-ru-ch').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#nameRuch').css('bregistration-color', 'red');
            $('#name-ru-ch').addClass('is-visible');
        }


        if (cost.length > 0 && costweightReg.test(cost)) {
            $('input#costch').css('bregistration-color', 'green');
            $('#cost-span-ch').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#costch').css('bregistration-color', 'red');
            $('#cost-span-ch').addClass('is-visible');
        }

        if (image_name.length > 0) {
            $('input#imagech').css('bregistration-color', 'green');
            $('#image-span-ch').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#imagech').css('bregistration-color', 'red');
            $('#image-span-ch').addClass('is-visible');
        }

        if ($numberCorrectField !== 3) {
            e.preventDefault();
        }
    });

    $('form#add-admin-form').on('click', function (e) {
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{5,}');

        var password = $('input#passwordAdmin').val();
        var repassword = $('input#repasswordAdmin').val();
        var login = $('input#loginAdmin').val();
        var $numberCorrectField = 0;
        var $numberCorrectPassword = 0;


        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#passwordAdmin').css('bregistration-color', 'green');
            $('input#passwordAdmin').removeClass('has-error').next('span#password-admin').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        } else {
            $('input#passwordAdmin').css('bregistration-color', 'red');
            $('input#passwordAdmin').addClass('has-error').next('span#password-admin').addClass('is-visible');
        }

        if (repassword.length >= 4 && passwordReg.test(repassword)) {
            $('input#repasswordAdmin').css('bregistration-color', 'green');
            $('input#repasswordAdmin').removeClass('has-error').next('span#repassword-admin').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        } else {
            $('input#repasswordAdmin').css('bregistration-color', 'red');
            $('input#repasswordAdmin').addClass('has-error').next('span#repassword-admin').addClass('is-visible');
        }

        if ($numberCorrectPassword === 2) {
            if (repassword !== password) {
                $('input#repasswordAdmin').css('bregistration-color', 'red')
                $('input#passwordAdmin').css('bregistration-color', 'red');
                $('input#repasswordAdmin').addClass('has-error');
                $('span#repassword-confirm').addClass('is-visible');
            } else {
                $('input#repasswordAdmin').css('bregistration-color', 'green')
                $('input#passwordAdmin').css('bregistration-color', 'green');
                $('input#repasswordAdmin').removeClass('has-error');
                $('span#repassword-confirm').removeClass('is-visible');
                $numberCorrectField++;
            }
        }

        if (login.length > 3 && loginReg.test(login)) {
            $('input#loginAdmin').css('bregistration-color', 'green');
            $('input#loginAdmin').removeClass('has-error').next('span#login-admin').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#loginAdmin').css('bregistration-color', 'red');
            $('input#loginAdmin').addClass('has-error').next('span#login-admin').addClass('is-visible');
        }

        if ($numberCorrectField !== 4) {
            e.preventDefault();
        }

    });

    $('form#change-password-form').on('click', function (e) {
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{5,}');

        var oldpassword = $('input#passwordOld').val();
        var newpassword = $('input#passwordNew').val();
        var repassword = $('input#repasswordNew').val();
        var $numberCorrectField = 0;
        var $numberCorrectPassword = 0;


        if (oldpassword.length >= 4 && passwordReg.test(oldpassword)) {
            $('input#passwordOld').css('bregistration-color', 'green');
            $('input#passwordOld').removeClass('has-error').next('span#password-old').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        } else {
            $('input#passwordOld').css('bregistration-color', 'red');
            $('input#passwordOld').addClass('has-error').next('span#password-old').addClass('is-visible');
        }

        if (newpassword.length >= 4 && passwordReg.test(newpassword)) {
            $('input#passwordNew').css('bregistration-color', 'green');
            $('input#passwordNew').removeClass('has-error').next('span#password-new').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        } else {
            $('input#passwordNew').css('bregistration-color', 'red');
            $('input#passwordNew').addClass('has-error').next('span#password-new').addClass('is-visible');
        }

        if (repassword.length >= 4 && passwordReg.test(repassword)) {
            $('input#repasswordNew').css('bregistration-color', 'green');
            $('input#repasswordNew').removeClass('has-error').next('span#repassword-new').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        } else {
            $('input#repasswordNew').css('bregistration-color', 'red');
            $('input#repasswordNew').addClass('has-error').next('span#repassword-new').addClass('is-visible');
        }

        if ($numberCorrectPassword === 3) {
            if (repassword !== newpassword) {
                $('input#repasswordNew').css('bregistration-color', 'red')
                $('input#passwordNew').css('bregistration-color', 'red');
                $('input#repasswordNew').addClass('has-error');
                $('span#repassword-new-confirm').addClass('is-visible');
            } else {
                $('input#repasswordNew').css('bregistration-color', 'green')
                $('input#passwordNew').css('bregistration-color', 'green');
                $('input#repasswordNew').removeClass('has-error');
                $('span#repassword-new-confirm').removeClass('is-visible');
                $numberCorrectField++;
            }
        }

        if ($numberCorrectField !== 4) {
            e.preventDefault();
        }

    });

    $('form#profile-form').on('click', function (e) {

        var name_surnameReg = new RegExp(`^([\u0410-\u042f]{1}[\u0430-\u044f]+)$|^([A-Z]{1}[a-z]+)$`);
        var emailReg = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");

        var name = $('input#profile-name').val();
        var surname = $('input#profile-surname').val();
        var email = $('input#profile-email').val();

        var $numberCorrectField = 0;

        if (name.length >= 2 && name_surnameReg.test(name)) {
            $('input#profile-name').css('bregistration-color', 'green');
            $('input#profile-name').removeClass('has-error').next('span#name-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#profile-name').css('bregistration-color', 'red');
            $('input#profile-name').addClass('has-error').next('span#name-profile-span').addClass('is-visible');
        }


        if (surname.length >= 2 && name_surnameReg.test(surname)) {
            $('input#profile-surname').css('bregistration-color', 'green');
            $('input#profile-surname').removeClass('has-error').next('span#surname-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#profile-surname').css('bregistration-color', 'red');
            $('input#profile-surname').addClass('has-error').next('span#surname-profile-span').addClass('is-visible');
        }


        if (email.length > 7 && emailReg.test(email)) {
            $('input#profile-email').css('bregistration-color', 'green');
            $('input#profile-email').removeClass('has-error').next('span#email-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#profile-email').css('bregistration-color', 'red');
            $('input#profile-email').addClass('has-error').next('span#email-profile-span').addClass('is-visible');
        }

        if ($numberCorrectField !== 3) {
            e.preventDefault();
        }
    });

    $('form#add-account-form').on('click', function (e) {

        var accountNumberReg = new RegExp("^[0-9]{16}$");
        var accountNumber = $('input#accountNumber').val();
        var $numberCorrectField = 0;

        if (accountNumber.length >= 16 && accountNumberReg.test(accountNumber)) {
            $('input#accountNumber').css('bregistration-color', 'green');
            $('input#accountNumber').removeClass('has-error').next('span#account-number').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#accountNumber').css('bregistration-color', 'red');
            $('input#accountNumber').addClass('has-error').next('span#account-number').addClass('is-visible');
        }

        if ($numberCorrectField !== 1) {
            e.preventDefault();
        }
    });

});

function viewAddReview(id) {
    $("#add_review").modal({backdrop: true});
}

function showEditService(id) {
    $("#change_service").modal({backdrop: true});
    $("#id_service").val(id);
    alert($("#id_service").val());
}
