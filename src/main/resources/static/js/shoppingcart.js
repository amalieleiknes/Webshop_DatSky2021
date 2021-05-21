$(function(){
    getShoppingcart();
    getTotalPrice();
    let emailcookie = getCookie("email");
    let customerIDcookie = getCookie("customerID");

    function getShoppingcart(){
        let customer = getCustomer();
        let tempUserID = getCookie("tempUserID");

        //If user on website is not logged in, check for products in temporary cart
        if(customer.customerID === null || customer.customerID.length === 0){
            $.get("/getCartItems", {customerID : tempUserID} ,function(products){
                console.log("products in temporary-cart",products);
                let output =
                    "<table class='table table-striped table-bordered'>" +
                    "<tr>" +
                    "<th>Product</th>" +
                    "<th>Description</th>" +
                    "<th>Price</th>" +
                    "<th>Delete from cart</th>" +
                    "</tr>";

                for (const product of products){
                    output +=
                        "<tr>" +
                        "<td>" + product.productName + "</td>" +
                        "<td>" + product.shortDescription + "</td>" +
                        "<td>" + product.price + " NOK" + "</td>" +
                        "<td><a class='btn btn-danger' onclick='deleteFromCart(" + product.productID + ")'>Remove</button></td>" +
                        "</tr>";
                }
                output += "</table>";
                $("#itemsInCart").empty().html(output);
            });

            //If user is logged in, get products from customers cart
        }else{
            $.get("/getCartItems", {customerID : customer.customerID} ,function(products){
                console.log("products in logged in cart: ",products);
                let output =
                    "<table class='table table-striped table-bordered'>" +
                    "<tr>" +
                    "<th>Product</th>" +
                    "<th>Description</th>" +
                    "<th>Price</th>" +
                    "<th>Delete from cart</th>" +
                    "</tr>";

                let i;
                for (i =0;i< products.length;i++){
                    console.log(products[i].productID);
                    output +=
                        "<tr>" +
                        "<td>" + products[i].productName + "</td>" +
                        "<td>" + products[i].shortDescription + "</td>" +
                        "<td>" + products[i].price + " NOK" + "</td>" +
                        "<td><a class='btn btn-danger' onclick='deleteFromCart(" + products[i].productID + ")'>Remove</button></td>" +
                        "</tr>";
                }
                output += "</table>";
                $("#itemsInCart").empty().html(output);
            });
        }
    }

    function getTotalPrice(){
        let customer = getCustomer();
        let tempUserID = getCookie("tempUserID");

        if(customer.customerID === null || customer.customerID.length === 0){
            $.get("/getTotalPrice", {customerID : tempUserID} ,function(totalprice){
                $("#totalCost").empty().html("Total price: " + totalprice + " NOK");
                $("#summarySubtotal").empty().html(totalprice + " NOK");
                $("#summaryDiscount").empty().html("- " + totalprice + " NOK");
            });
        }else{
            $.get("/getTotalPrice", {customerID : customer.customerID} ,function(totalprice){
                $("#totalCost").empty().html("Total price: " + totalprice + " NOK");
                $("#summarySubtotal").empty().html(totalprice + " NOK");
                $("#summaryDiscount").empty().html("- " + totalprice + " NOK");
            });
        }
    }

    $("#goToCheckout").click(function(){
        if (emailcookie === "" && customerIDcookie === ""){
            window.location.href = "checkoutOptions.html";
        }
        else{
            window.location.href = "checkout.html";
        }
    });




});


function deleteFromCart(productID) {
    let customerID = getCookie("customerID")
    let tempUserID = getCookie("tempUserID");

    if(customerID===""){
        if(tempUserID===""){
            console.log("The userID (temporary or if logged in) has been deleted. " +
                "Please go to http://localhost:10222/index.html to get a new cookie.");
        }
        else {
            $.post("/deleteFromCart", {customerID: tempUserID, productID: productID}, function (string) {
                console.log(string);
            });
        }
    }
    else {
        $.post("/deleteFromCart", {customerID: customerID, productID: productID}, function (string) {
            console.log(string);
        });
    }
    location.reload();
}