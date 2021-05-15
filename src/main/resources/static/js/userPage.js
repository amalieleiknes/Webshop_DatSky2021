$(function(){
    getOrderHistory();

    function getOrderHistory(){
        let customer = getCustomer();

        $.post("/order/getOrdersByCustomer", {customerID: customer.customerID} ,function(orderList){

            console.log("Previous orders: ");
            let output =
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>OrderID</th>" +
                "<th>Order Date</th>" +
                "<th>Totalprice</th>" +
                "<th>Number of items</th>" +
                "</tr>";

            // TODO: Må finne hva vi skal vise av info frs tidligere ordre - se docs for forslag
            for (const order of orderList){
                console.log(order.price);
                output +=
                    "<tr>" +
                    "<td>" + order.productName + "</td>" +
                    "<td>" + order.shortDescription + "</td>" +
                    "<td>" + order.price + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#orderHistory").html(output);
        });
    }


});