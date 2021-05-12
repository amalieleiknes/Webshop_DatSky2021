$(function(){
    getOrderHistory();

    function getOrderHistory(){
        let customer = getCustomer();
        $.get("/getOrdersByCustomer", {customerID : customer.customerID} ,function(orders){
            console.log("Previous orders: ", orders);
            let output =
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>Date</th>" +
                "<th>Number of products</th>" +
                "<th>Totalprice</th>" +
                "</tr>";

            // TODO: Må finne hva vi skal vise av info frs tidligere ordre - se docs for forslag
            for (const order of orders){
                output +=
                    "<tr>" +
                    "<td>" + order.productName + "</td>" +
                    "<td>" + order.shortDescription + "</td>" +
                    "<td>" + order.price + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#orderHistory").empty().html(output);
        });
    }


});