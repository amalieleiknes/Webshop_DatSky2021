"use strict";
$(function() {
    $("#adminfailedLogIn").hide();
    $("#failedLogIn").hide();
    $("#failedProductAddtoCart").hide();

    let emailcookie = getCookie("email");
    let customerIDcookie = getCookie("customerID");
    let navCardElement = document.getElementById("nav");
    let content;


    // checking if customer is logged in or not, and then gives the dynamic navigationcontent based on this info
    if (emailcookie === "" && customerIDcookie === "") {
        content =
            "<div class='userLogInReg'>" +
                "<a href='registerUser.html' tabIndex=''>" +
                    "<div class='link'>Register as user</div>" +
                "</a>" +
                "<input type='text' class='input' id='customeremail' placeholder='email' name='email'/>" +
                "<input type='text' class='input' id='customerpassword' placeholder='password' name='password'/>" +
                "<button id='customerLogOnBtn'>Log in as user</button>" +
                "<br/>" +
            "</div>"
    } else {
        content =
            "<div class='userLogInReg'>" +
            "<button id='logOutbtn'>Log out</button>" +
            "</div>";

    }
    navCardElement.innerHTML = content;


    // check customers login-info
    $("#customerLogOnBtn").click(function() {
        console.log("logging in...");

        const email = $("#customeremail").val();
        const password = $("#customerpassword").val();

        // if input fields are empty, show failedmsg
        if(email.length === 0 || email === ' ' || email === null || password.length === 0){
            $("#failedLogIn").show();
        }
        else{
            $("#failedLogIn").hide();

            let userLoggingIn = {
                email: email,
                password: password
            };
            // else, send userinfo into API and find out if the info is correct
            $.post("/customers/logOnCustomer", {email: userLoggingIn.email, password: userLoggingIn.password}, function(foundCustomer) {
                if(foundCustomer === null){
                    $("#failedLogIn").show();
                }
                else{
                    $("#failedLogIn").hide();
                    console.log("CustomerID til customer som ble funnet: ", foundCustomer.customerID);
                    setCookie("email", email, 1);
                    setCookie("customerID", foundCustomer.customerID, 1);
                    location.reload();
                }
            });
        }

    });

    //logging out customer/admin
    $("#logOutbtn").click(function(){
        logOut();
    });


    // check admins login-info TODO: ikke bra sikkerhet. Bør sjekkes i java kanskje?
    $("#adminlogOnbtn").click(function() {
        logOnAdmin();
    });
});


// function to print out all prducts on the startpage
function getNumberOfCartItems() {
    let customerID = getCookie("customerID");
    $.get("/getNumberOfCartItems", {customerID: customerID}, function(numberOfProducts){
        console.log("Antall i cart: ", numberOfProducts);
        let element = document.getElementById("cartOverview");

        element.innerHTML =
            "<img src='images/cart.png' alt='shoppingcart' width='50' height='50'>" +
            "<span style='font-size: 30px'>" + numberOfProducts + "</span>";
    });
}

function logOut(){
    $(location).attr('href', 'index.html');
    setCookie("email", null, 0);
    setCookie("customerID", null, 0);
    deleteCustomerCookie();
    location.reload();
}

function logOnAdmin(){
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
            window.location.href= 'adminPage.html';
        }
    }
}

function logOnUser(){

}

