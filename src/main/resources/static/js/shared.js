"use strict";
$(function(){

    let logonElement = document.getElementById("userLogInReg");
    let customer = getCustomer();


    //TODO: if not cookie i nettleseren
    // if not logged in
    if(customer.customerID === null || customer.customerID === ''){
        logonElement.innerHTML =
            "<div class='content'>" +
                "<input type='text' class='form-control' id='username' placeholder='username' name='username'/>\n" +
                "<input type='text' class='form-control' id='password' placeholder='password' name='password'/>\n" +
                "<button type='submit'>Log in as user</button>\n" +

                "<span id='failedLogIn'>Incorrect logininfo</span>" +

                "\n" +
                "<a href='registerUser.html' tabindex=''>\n" +
                    "<div class='link'>Register as user</div>\n" +
                "</a>" +
            "</div>";

    }

    // else user logged in
    else {
        let customerLoggedIn = customer.username;

        logonElement.innerHTML =
            "<div class='content'>" +
                "Logged in as: <span id='customerLoggedIn'></span>" +
            "</div>";




        let logOut = {
            text: "Sign out",
            getHTML: function () {
                return this.text;
            }
        }
    }

});