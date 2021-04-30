$(function(){
    getShoppingcart();

    function getShoppingcart(){
        let customer = getCustomer();
        $.get("/getCartItems", {customerID : customer.customerID} ,function(products){
            console.log("products in cart",products);
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
                    "<td>" + product.price + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#itemsInCart").empty().html(output);
        });
    }


});