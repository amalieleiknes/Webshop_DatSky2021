"use strict";
$(function() {
    $("#adminfailedLogIn").hide();
    $("#failedLogIn").hide();

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
                console.log("trying to set cookie...")
                setCookie("email", email, 1);
                setCookie("userID", customer.customerID, 1);
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



    // TODO: function that will switch between login and logout, depending on the state of the user
    function logon() {
        let logonElement = document.getElementById("userLogInReg");
        let customer = getCustomer();


        //TODO: if not cookie i nettleseren
        // if not logged in
        if (customer.customerID === null || customer.customerID === '') {
            let content =
                "<div class='content'>" +
                "<input type='text' class='form-control' id='username' placeholder='username' name='username'/>\n" +
                "<input type='text' class='form-control' id='password' placeholder='password' name='password'/>\n" +
                "<button type='submit'>Log in as user</button>\n" +

                "<span id='failedLogIn'>Incorrect logininfo</span>" +
                "<a href='registerUser.html' tabindex='3'>\n" +
                "<div class='link'>Register as user</div>\n" +
                "</a>" +
                "</div>";

            $("#userLogInReg").empty().html(content);

        }

        // else user logged in
        else {
            logonElement.innerHTML =
                "<div class='content'>" +
                "Logged in as: <span id='customerLoggedIn'></span>" +
                "</div>";

            let customerLoggedIn = customer.username;

        }

    }
});