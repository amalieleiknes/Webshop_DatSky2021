
// Add new customer/user
function addCustomer(){
    let welcomeElement = document.getElementById("registerInfo");
    welcomeElement.innerHTML = "";

    const newCustomer = {
        firstName        : $("#firstname").val(),
        lastName         : $("#lastname").val(),
        addressLine      : $("#address").val(),
        zipcode          : $("#zipcode").val(),
        telephone        : $("#phone").val(),
        email            : $("#email").val(),
        password         : $("#pwd").val(),

    };

    if (newCustomer.firstName.length === 0 ||
        newCustomer.lastName.length === 0 ||
        newCustomer.addressLine.length === 0||
        newCustomer.zipcode.length === 0 ||
        newCustomer.telephone.length === 0 ||
        newCustomer.email.length === 0 ||
        newCustomer.password.length === 0){
        console.log("Some more fields need to be filled in.");
        welcomeElement.innerHTML = "Some more fields need to be filled in. Please check";
    }

    if (newCustomer.password!== $("#pwdcheck").val()){
        console.log("Passwords are not equal");
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

