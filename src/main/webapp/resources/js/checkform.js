function checkForm(form) {
    valid = true;

    if (document.getElementById('name').value.replace(/^\s*/, '').replace(/\s*$/, '') == "") {
        document.getElementById('err_name').innerHTML = 'Enter your first name';
        valid = false;
    }
    else {
        document.getElementById('err_name').innerHTML = '';
    }
    ;

    if (document.getElementById('surname').value.replace(/^\s*/, '').replace(/\s*$/, '') == "") {
        document.getElementById('err_surname').innerHTML = 'Enter your last name';
        valid = false;
    } else {
        document.getElementById('err_surname').innerHTML = '';
    }
    ;

    if (document.getElementById('bith_year').value == "" ||
        document.getElementById('bith_day').value == "") {

        document.getElementById('err_bithdate').innerHTML = 'Enter your birthdate';
        valid = false;
    } else {
        document.getElementById('err_bithdate').innerHTML = '';
    }
    ;

    if (document.getElementById('phone').value == "") {
        document.getElementById('err_phone').innerHTML = 'Enter your phone';
        valid = false;
    } else if (document.getElementById('phone').value.indexOf("_") != -1) {
        document.getElementById('err_phone').innerHTML = 'Wrong phone, please correct this field';
        valid = false;
    } else {
        document.getElementById('err_phone').innerHTML = '';
    }
    ;

    pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    emailAddress = document.getElementById('email').value;
    if (emailAddress == "") {
        document.getElementById('err_email').innerHTML = 'Enter your email';
        valid = false;
    } else if (!pattern.test(emailAddress)) {
        document.getElementById('err_email').innerHTML = 'Wrong email, please correct this field';
        valid = false;
    } else {
        document.getElementById('err_email').innerHTML = '';
    }
    ;

    if (document.getElementById('country').value == "") {
        document.getElementById('err_country').innerHTML = 'Choose your country';
        valid = false;
    } else {
        document.getElementById('err_country').innerHTML = '';
    }
    ;

    if (document.getElementById('city').value.replace(/^\s*/, '').replace(/\s*$/, '') == "") {
        document.getElementById('err_city').innerHTML = 'Enter your city';
        valid = false;
    } else {
        document.getElementById('err_city').innerHTML = '';
    }
    ;

    return valid;
};