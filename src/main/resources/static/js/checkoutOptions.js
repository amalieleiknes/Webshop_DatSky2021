$(function(){
    let output;

    $("#optionLogInBtn").click(function(){
        $("#failedLogInOptions").hide();
        $("#myDropdown").hide();
        output =
            "<div class='userLogIn'>" +
                "<h2>Type in your email and password to log in</h2>" +
                "</br>" +
                "<input type='text' class='input' id='customeremailOptions' placeholder='email' name='email'/>" +
                "<input type='text' class='input' id='customerpasswordOptions' placeholder='password' name='password'/>" +
                "<button class='customerLogIn' onclick='customerLogInFromOptions()' id='customerLogOnBtn'>Log in as user</button>" +
                "</br>" +
                "<span id='failedLogInOptions'>Incorrect login info</span>" +
            "</div>";

        $("#optionsHeader").hide();
        $("#optionLogInBtn").hide();
        $("#optionRegisterBtn").hide();
        $("#optionGuestBtn").hide();
        $("#failedLogInOptions").hide();

        document.getElementById("optionsLogInRegForms").innerHTML = output;
        $("#failedLogInOptions").hide();
    });

    $("#optionRegisterBtn").click(function(){
        output =
            "<div class='regUserContent' id='regUserContent'>" +
                "<div class='userContent' id='userContent'>" +
                    "<h2>Type in your info here to register</h2>" +
                "</br>" +
                "<div class='userDetails' id='userDetails'>" +
                    "<table class='table-registeruser' id='table-registeruser'>" +
                        "<tr>" +
                            "<td><input type='text' id='firstname' placeholder='First name' name='First name'/></td>" +
                            "<td><input type='text' id='lastname' placeholder='Last name' name='Last name'/></td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td><input type='text' id='address' placeholder='Street address' name='Street address'/></td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td><input type='text' id='zipcode' placeholder='Zip code' name='Zip code'/></td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td><input type='text' id='phone' placeholder='Phone' name='Phone'/></td>" +
                            "<td><input type='text' id='email' placeholder='Email address' name='Email address'/></td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td><input type='text' id='pwd' placeholder='Password' name='Password'/></td>" +
                            "<td><input type='text' id='pwdcheck' placeholder='Retype your password' name='Retype your password'/></td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td></br><button class='registerbtn' onclick='addCustomer()' id='registerUser'>Register as user</button></br></td>" +
                            "<td><span id='registerUserError'></span></td>" +
                        "</tr>" +
                    "</table>" +
                "</div>" +
            "</div>" +

            "<div class='registerInfo' id='registerInfo'></div>" +
        "</div>";

        $("#optionsHeader").hide();
        $("#optionLogInBtn").hide();
        $("#optionRegisterBtn").hide();
        $("#optionGuestBtn").hide();
        document.getElementById("optionsLogInRegForms").innerHTML = output;
    });

});

function customerLogInFromOptions(){
// check customers login-info
    /*$("#customerLogOnBtn").click(function() {*/
    let tempUserID = getCookie("tempUserID");
    console.log("logging in...");

    const email = $("#customeremailOptions").val();
    const password = $("#customerpasswordOptions").val();

    // if input fields are empty, show failedmsg
    if(email.length === 0 || email === ' ' || email === null || password.length === 0){
        $("#failedLogInOptions").show();
    }
    else{
        $("#failedLogInOptions").hide();

        // else, send userinfo into API and find out if the info is correct
        const login  = {
            email       : email,
            password    : password,
            tempUserID  : tempUserID
        }

        $.post("customers/checkIfValidCustomerLoginInfo", login, function(customerID){
            if(customerID!=="FAIL"){
                console.log("Setting cookie, user exists...")
                $("#failedLogInOptions").hide();

                setCookie("email", email, 1);
                setCookie("customerID", customerID, 1);
                deleteCookie("adminusername");
                deleteCookie("adminpassword");

                deleteCookie("tempUserID");
                window.location.href = "checkout.html";
            }
            else{
                $("#failedLogInOptions").show();
            }
        });
    }
}