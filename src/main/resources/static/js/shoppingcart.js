$(function(){
    getShoppingcart();

    function getShoppingcart(){
        $.get("/getShoppingcart", function(products){
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
                    "<td>" + product.name + "</td>" +
                    "<td>" + product.shortDescription + "</td>" +
                    "<td>" + product.price + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#shoppingcart").empty().html(output);
        });
    }


});