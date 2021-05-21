
// Add new customer/user
function addCustomer(){
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = "";

    const newCustomer = {
        firstname        : $("#firstname").val(),
        lastname         : $("#lastname").val(),
        address          : $("#address").val(),
        zipcode          : $("#zipcode").val(),
        telephone        : $("#phone").val(),
        email            : $("#email").val(),
        password         : $("#pwd").val(),

    };



    if (newCustomer.firstname.length === 0 ||
        newCustomer.lastname.length === 0 ||
        newCustomer.address.length === 0||
        newCustomer.zipcode.length === 0 ||
        newCustomer.telephone.length === 0 ||
        newCustomer.email.length === 0 ||
        newCustomer.password.length === 0){
        welcomeElement.innerHTML = "Some more fields need to be filled in. Please check";
    }

    else if (newCustomer.password!== $("#pwdcheck").val()){
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



// function to display message about successful adding of customer
function customerAdded(cust){
    const content = "<h2> Welcome, " + cust.firstname + "! Please sign in in the top navigation-bar, or continue as a guest if you don" +
        "t want to link your orders to your account. </h2>";
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = content;

    let contentLogIn =
        "<div class='userLogIn'>" +
        "<input type='text' class='input' id='customeremailOptions' placeholder='email' name='email'/>" +
        "<input type='text' class='input' id='customerpasswordOptions' placeholder='password' name='password'/>" +
        "<button class='customerLogIn' onclick='customerLogInFromOptions()' id='customerLogOnBtn'>Log in as user</button>" +
        "</div>";
    document.getElementById("optionsLogInRegForms").innerHTML = contentLogIn;
}

