$(function(){
    getShoppingcart();
    getTotalPrice();

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
                    "</tr>";

                for (const product of products){
                    output +=
                        "<tr>" +
                        "<td>" + product.productName + "</td>" +
                        "<td>" + product.shortDescription + "</td>" +
                        "<td>" + product.price + " NOK" + "</td>" +
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
                    "</tr>";

                for (const product of products){
                    output +=
                        "<tr>" +
                        "<td>" + product.productName + "</td>" +
                        "<td>" + product.shortDescription + "</td>" +
                        "<td>" + product.price + " NOK" + "</td>" +
                        "</tr>";
                }
                output += "</table>";
                $("#itemsInCart").empty().html(output);
            });
        }
    }

    function getTotalPrice(){
        let customer = getCustomer();
        $.get("/getTotalPrice", {customerID : customer.customerID} ,function(totalprice){
            $("#totalCost").empty().html("Total price: " + totalprice + " NOK");
            $("#summarySubtotal").empty().html(totalprice + " NOK");
            $("#summaryDiscount").empty().html("- " + totalprice + " NOK");
        });
    }
});