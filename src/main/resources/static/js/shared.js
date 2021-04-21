"use strict";
$(function() {
    logon();


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