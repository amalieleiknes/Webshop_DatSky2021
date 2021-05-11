
// Add new customer/user
function addCustomer(){
    const newCustomer = {
        firstName        : $("#firstname").val(),
        lastName         : $("#lastname").val(),
        addressLine      : $("#address").val(),
        zipcode          : $("#zipcode").val(),
        telephone        : $("#phone").val(),
        email            : $("#email").val(),
        password         : $("#pwd").val(),
        passwordcheck    : $("#pwdcheck").val()
    };

    if (newCustomer.firstName.length === 0 ||
        newCustomer.lastName.length === 0 ||
        newCustomer.addressLine.length === 0||
        newCustomer.zipcode.length === 0 ||
        newCustomer.telephone.length === 0 ||
        newCustomer.email.length === 0 ||
        newCustomer.password.length === 0){
        console.log("Some fields need to be filled in.");
    }
    if (newCustomer.password!== newCustomer.passwordcheck){
        console.log("Passwords are not equal");
    }

    else{
        $.post("customers/addCustomer", newCustomer, function(result){
            if(result !== "Customer added succsessfully"){
                console.log("Could not add customer (customer is null)")
            }
            else {console.log("Success!")}
        })
    }
}

