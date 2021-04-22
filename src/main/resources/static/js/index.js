"use strict";
$(function(){
    getAllProducts();

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



    // check customers login-info
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


    function getAllProducts(){

        $.get("/getAllProducts", function(products){
            let output = "<table class='products'>";

            for (const product of products){
                output +=
                    "<tr>" +
                    "<td>" + product.name + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#allProducts").empty().html(output);
        });
    }



});