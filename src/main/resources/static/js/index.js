"use strict";
$(function(){
    $("#failedLogIn").hide();

    $("#regCustomer").click(function() {
        const email = $("#email").val();
        if(email.length === 0 || email === ' ' || email === null){
            $("#failedLogIn").show();
        }
        else{
            $("#failedLogIn").hide();
            $.post("/registerUser", {email: email}, function(customer) {
                $("#username").val("");
                setCookie("email", email, 1);
                setCookie("userID", customer.customerID, 1);
            });
        }
    });
});