$(function(){
    getOrderHistory();


    function getOrderHistory() {
        let customer = getCustomer();

        $.post("/orders/getOrdersByCustomer", {customerID: customer.customerID}, function (orderList) {
            let i;
            console.log("Previous orders are printing... ");

            let output =
                "<h1>Order history</h1>" +
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>OrderID</th>" +
                "<th>Order Date</th>" +
                "<th>Totalprice</th>" +
                "<th>Number of items</th>" +
                "<th>Press to see content</th>" +
                "</tr>";

            let line = 1;
            $.each(orderList, function( key, order) {
                    output +=
                        "<tr>" +
                        "<td><input type='text' disabled readonly id='orderID"+line+"' size='3' value='"+order.orderID+"'/></td>" +
                        "<td>" + order.orderDate + "</td>" +
                        "<td>" + order.totalPrice + "</td>" +
                        "<td>" + order.amount + "</td>" +
                        "<td><a class='btn btn-success' onclick='getOrderContent("+line+")'>See ordercontent</button></td>" +
                        "</tr>";
                    line++;
            });

            output += "</table>";
            $("#orderHistory").empty().html(output);
        });
    }
});


function getOrderContent(line){
    const orderID= $("#orderID" + line).val();

    $.post("/orders/getOrdercontent", {orderID: orderID} ,function(orderContentList){
        let output =
            "<h1>Order #"+orderID+"</h1>" +
            "<table class='table table-striped table-bordered'>" +
            "<tr>" +
            "<th>ProductID</th>" +
            "<th>Product Name</th>" +
            "<th>Price</th>" +
            "</tr>";

        console.log("Previous orders' content is printing... ");
        let i;
        for (i=0; i<orderContentList.length; i++){
            console.log(orderContentList[i].orderID);
            output +=
                "<tr>" +
                "<td>" + orderContentList[i].productID + "</td>" +
                "<td>" + orderContentList[i].productName + "</td>" +
                "<td>" + orderContentList[i].price + "</td>" +
                "</tr>";
        }
        output += "</table>";
        $("#orderContentHistory").html(output);
    });

}