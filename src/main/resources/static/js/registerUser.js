$(function(){
    $("#failedLogInOptions").hide();
});

// Add new customer/user
function addCustomer(){
    let firstNameElement = document.getElementById("firstname");
    let lastNameElement = document.getElementById("lastname");
    let addressElement = document.getElementById("address");
    let zipcodeElement = document.getElementById("zipcode");
    let phoneElement = document.getElementById("phone");
    let emailElement = document.getElementById("email");
    let pwdElement = document.getElementById("pwd");
    let pwdcheckElement = document.getElementById("pwdcheck");

    if(isValid(firstNameElement, "First name", "registerUserError") &&
        isValid(lastNameElement, "Last name", "registerUserError") &&
        isValid(addressElement, "Address", "registerUserError") &&
        isValidNumber(zipcodeElement, "Zip code", "registerUserError") &&
        isValid(phoneElement, "Phone", "registerUserError") &&
        isValid(emailElement, "Email", "registerUserError") &&
        isValid(pwdElement, "Password", "registerUserError") &&
        isValid(pwdcheckElement, "Password check", "registerUserError")){

        let welcomeElement = document.getElementById("registerInfo");
        welcomeElement.innerHTML = "";

        const newCustomer = {
            firstname        : $("#firstname").val(),
            lastname         : $("#lastname").val(),
            address          : $("#address").val(),
            zipcode          : $("#zipcode").val(),
            telephone        : $("#phone").val(),
            email            : $("#email").val(),
            password         : $("#pwd").val()
        };

        if (newCustomer.password!== $("#pwdcheck").val()){
            welcomeElement.innerHTML = "Passwords are not equal. Please check";
        }
        else{
            $.post("customers/checkZipcode", newCustomer, function(status) {
                console.log(newCustomer.zipcode);
                console.log(status);
                if(status==="OK"){
                    $.post("customers/addCustomer", newCustomer, function (result) {
                        if (result === "OK") {
                            customerAdded(newCustomer);

                            // setting fields to empty after registering
                            $("#firstname").val("");
                            $("#lastname").val("");
                            $("#address").val("");
                            $("#zipcode").val("");
                            $("#phone").val("");
                            $("#email").val("");
                            $("#pwd").val("");
                            $("#pwdcheck").val("");

                        } else {
                            let welcomeElement = document.getElementById("registerInfo");
                            welcomeElement.innerHTML = result;
                            console.log(result);
                        }
                    });
                }
                else {
                    welcomeElement.innerHTML = "That zipcode is not valid. Please check";
                }
            });
        }
    }
}

// function to display message about successful adding of customer
function customerAdded(cust){
    $("#failedLogInOptions").hide();

    const content = "<h2> Welcome, " + cust.firstname + "! Please sign in in the top navigation-bar, or continue as a guest if you don" +
        "t want to link your orders to your account. </h2>";
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = content;

    let contentLogIn =
        "<div class='userLogIn'>" +
        "<h2>Type in your email and password to log in</h2>" +
        "</br>" +
        "<input type='text' class='input' id='customeremailOptions' placeholder='email' name='email'/>" +
        "<input type='text' class='input' id='customerpasswordOptions' placeholder='password' name='password'/>" +
        "<button class='customerLogIn' onclick='customerLogInFromOptions()' id='customerLogOnBtn'>Log in as user</button>" +
        "</br>" +
        "<span id='failedLogInOptions'>Incorrect login info</span>" +
        "</div>";
    document.getElementById("optionsLogInRegForms").innerHTML = contentLogIn;
    $("#failedLogInOptions").hide();
}