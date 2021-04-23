"use strict";
$(function() {
    $("#adminfailedLogIn").hide();
    $("#failedLogIn").hide();

    let emailcookie = getCookie("email");
    let customerIDcookie = getCookie("customerID")
    let navCardElement = document.getElementById("nav");
    let content;

    if(emailcookie==="" && customerIDcookie === ""){
        content =
            "<div class='userLogInReg'>" +
                "<a href='registerUser.html' tabIndex=''>" +
                    "<div class='link'>Register as user</div>" +
                "</a>" +
                "<input type='text' class='input' id='email' placeholder='email' name='email'/>" +
                "<input type='text' class='input' id='password' placeholder='password' name='password'/>" +
                "<button id='logOnbtn'>Log in as user</button>"+
                "<br/>" +
            "</div>"
    }

    else{
        content =
            "<div class='userLogInReg'>" +
                "<button id='logOutbtn'>Log out</button>" +
            "</div>";

    }

    navCardElement.innerHTML = content;


$("#logOutbtn").click(function(){
    $(location).attr('href', 'index.html');
    setCookie("email", null, 0);
    setCookie("customerID", null, 0);
    deleteCustomerCookie();
    location.reload();
});

// check customers login-info
$("#logOnbtn").click(function() {

    console.log("logging in...")

    const email = $("#email").val();
    const password = $("#password").val();

    // if input fields are empty, show failedmsg
    if(email.length === 0 || email === ' ' || email === null || password.length === 0){
        $("#failedLogIn").show();
    }
    else{
        $("#failedLogIn").hide();

        let userLoggingIn = {
            email: email,
            password: password
        }
        // else, send userinfo into API and find out if the info is correct
        $.post("/customers/logOnCustomer", userLoggingIn, function(customer) {
            if(customer=== null){
                $("#failedLogIn").show();
            }
            else{
                $("#failedLogIn").hide();
                setCookie("email", email, 1);
                setCookie("customerID", customer.customerID, 1);
                location.reload();
            }
        });
    }
});

// check admins login-info TODO: not great security
$("#adminlogOnbtn").click(function() {

    console.log("logging in admin...")



    const adminusername = $("#adminusername").val();
    const adminpassword = $("#adminpassword").val();

    // if input fields are empty, show failedmsg
    if(adminusername.length === 0 || adminusername === ' ' || adminusername === null || adminpassword.length === 0){
        $("#adminfailedLogIn").show();
    }
    else{
        $("#adminfailedLogIn").hide();

        if(adminusername === "admin" && adminpassword === "admin"){
            console.log("trying to set cookie...")
            setCookie("adminusername", adminusername, 1);
            setCookie("adminpassword", adminpassword, 1);
            window.location.href= 'adminPage.js';
        }
    }
});


});