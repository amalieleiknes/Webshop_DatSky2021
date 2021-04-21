"use strict";
$(function(){
    getAllProducts();

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