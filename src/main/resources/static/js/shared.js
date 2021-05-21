"use strict";
$(function() {
    getNumberOfCartItems();
    $("#adminfailedLogIn").hide();
    $("#failedLogIn").hide();
    $("#failedProductAddtoCart").hide();

    let emailcookie = getCookie("email");
    let customerIDcookie = getCookie("customerID");
    let userIconDropDown = document.getElementById("userIconDropdownmenu");
    let content;


    // checking if customer is logged in or not, and then gives the dynamic navigationcontent based on this info
    if (emailcookie === "" && customerIDcookie === "") {
        content =
            "<img src='images/Usericon.png' id='usericon' alt='shoppingcart' onclick='dropMenu()' width='70' height='70'>" +
            "<div id='myDropdown' class='dropdown-content'>" +
                "<div class='userLogInReg'>" +
                    "<a href='registerUser.html' tabIndex=''>" +
                        "<div class='link'>Register as user</div></a>" +
                    "<input type='text' class='input' id='customeremail' placeholder='email' name='email'/>" +
                    "<input type='text' class='input' id='customerpassword' placeholder='password' name='password'/>" +
                    "<button id='customerLogOnBtn'>Log in as user</button>" +
                    "<br/>" +
                "</div>" +
            "</div>";
    }

    else {
        content =
            "<img src='images/Usericon.png' id='usericon' alt='shoppingcart' onclick='dropMenu()' width='80' height='80'>" +
            "<div id='myDropdown' class='dropdown-content'>" +
                "<button id='logOutbtn'>Log out</button>" +
                "<a href='userPage.html' tabIndex=''>" +
                "<div class='link'>See order history</div></a>" +
            "</div>";

    }
    userIconDropDown.innerHTML = content;


    // check customers login-info
    $("#customerLogOnBtn").click(function() {
        let tempUserID = getCookie("tempUserID");
        console.log("logging in...");

        const email = $("#customeremail").val();
        const password = $("#customerpassword").val();

        // if input fields are empty, show failedmsg
        if(email.length === 0 || email === ' ' || email === null || password.length === 0){
            $("#failedLogIn").show();
        }
        else{
            $("#failedLogIn").hide();

            // else, send userinfo into API and find out if the info is correct
            const login  = {
                email       : email,
                password    : password,
                tempUserID  : tempUserID
            }

            $.post("customers/checkIfValidCustomerLoginInfo", login, function(customerID){
                if(customerID!=="FAIL"){
                    console.log("Setting cookie, user exists...")
                    $("#failedLogIn").hide();

                    setCookie("email", email, 1);
                    setCookie("customerID", customerID, 1);
                    deleteCookie("adminusername");
                    deleteCookie("adminpassword");

                    deleteCookie("tempUserID");
                    location.reload();
                }
                else{
                    $("#failedLogIn").show();
                }
            });
        }
    });

    //logging out customer
    $("#logOutbtn").click(function(){
        logOut();
    });

    // if button is clicked, call on function to log in admin
    $("#adminlogOnbtn").click(function() {
        logOnAdmin();
    });
});


// function to print out all prducts on the startpage
function getNumberOfCartItems() {
    let customerID = getCookie("customerID");

    //If user is not logged in, get items from temporary cart
    if(customerID.length === 0){
        let tempUserID = getCookie("tempUserID");
        $.get("/getNumberOfCartItems", {customerID: tempUserID}, function(numberOfProducts){
            console.log("Antall i cart: ", numberOfProducts);
            let element = document.getElementById("cartOverview");

            element.innerHTML =
                "<img src='images/Shoppingcart.png' alt='shoppingcart' width='70' height='70'>" +
                "<span style='font-size: 40px'>" + numberOfProducts + "</span>";
        });

    //User is logged in
    }else {
        $.get("/getNumberOfCartItems", {customerID: customerID}, function (numberOfProducts) {
            console.log("Antall i cart: ", numberOfProducts);
            let element = document.getElementById("cartOverview");

            element.innerHTML =
                "<img src='images/Shoppingcart.png' alt='shoppingcart' width='80' height='80'>" +
                "<span style='font-size: 30px'>" + numberOfProducts + "</span>";
        });
    }
}

// function to log out a customer
function logOut(){
    $(location).attr('href', 'index.html');
    setCookie("email", null, 0);
    setCookie("customerID", null, 0);
    setCookie("tempUserID", null, 0);
    deleteCookie("customerID");
    deleteCookie("email");
    deleteCookie("tempUserID");
    location.reload();
}

//function to log on admin - first checking if the data is valid
function logOnAdmin(){
    const adminusername = $("#adminusername").val();
    const adminpassword = $("#adminpassword").val();

    // if input fields are empty, show failedmsg
    if(adminusername.length === 0 || adminusername === ' ' || adminusername === null || adminpassword.length === 0){
        $("#adminfailedLogIn").show();
    }
    else{
        $("#adminfailedLogIn").hide();
        $.get("products/checkAdmin", {username: adminusername, password: adminpassword}, function(valid) {
            console.log(valid);
            if (valid === true) {
                console.log("trying to set cookie...")
                deleteCookie("customerID");
                deleteCookie("email");
                deleteCookie("tempUserID");
                setCookie("adminusername", adminusername, 1);
                setCookie("adminpassword", adminpassword, 1);
                deleteCookie()
                window.location.href = 'adminPage.html';
            }
            else{
                console.log("Failed attempt to log into adminpage.");
            }
        });

    }
}

// fucntion to show the dropdownmenu when clicked on
function dropMenu() {
    document.getElementById("myDropdown").classList.toggle("showMenu");
}

