
// Add new customer/user
function addCustomer(){
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = "";

    const newCustomer = {
        firstname        : $("#firstname").val(),
        lastname         : $("#lastname").val(),
        address          : $("#address").val(),
        zipcode          : $("#zipcode").val(),
        tlphNumber       : $("#phone").val(),
        email            : $("#email").val(),
        password         : $("#pwd").val(),

    };

    if (newCustomer.firstname.length === 0 ||
        newCustomer.lastname.length === 0 ||
        newCustomer.address.length === 0||
        newCustomer.zipcode.length === 0 ||
        newCustomer.tlphNumber.length === 0 ||
        newCustomer.email.length === 0 ||
        newCustomer.password.length === 0){
        welcomeElement.innerHTML = "Some more fields need to be filled in. Please check";
    }

    if (newCustomer.password!== $("#pwdcheck").val()){
        welcomeElement.innerHTML = "Passwords are not equal. Please check";
    }

    else{
        $.post("customers/addCustomer", newCustomer, function(result){
            if(result === "Customer added succsessfully"){
                console.log("Success!");
                customerAdded(newCustomer);
            }
            else {
                console.log("Could not add customer (customer is null)");
            }
        });
    }
}


// function to display message about successful adding of customer
function customerAdded(cust){
    const content = "<h2> Welcome, " + cust.firstName + "! Please sign in in the top navigation-bar, or continue as a guest if you don" +
        "t want to link your orders to your account. </h2>";
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = content;
}

