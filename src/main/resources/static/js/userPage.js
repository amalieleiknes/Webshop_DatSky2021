$(function(){
    getOrderHistory();

    function getOrderHistory(){
        let customer = getCustomer();
        $.get("/getOrdersByCustomer", {customerID : customer.customerID} ,function(orders){
            console.log("Previous orers: ",orders);
            let output =
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>Date</th>" +
                "<th>Number of products</th>" +
                "<th>Totalprice</th>" +
                "</tr>";

            // TODO: Må finne hva vi skal vise av info frs tidligere ordre - se docs for forslag
            for (const product of products){
                output +=
                    "<tr>" +
                    "<td>" + product.productName + "</td>" +
                    "<td>" + product.shortDescription + "</td>" +
                    "<td>" + product.price + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#orderHistory").empty().html(output);
        });
    }


});