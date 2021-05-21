$(function(){
    getOrderHistory();


    function getOrderHistory() {
        let customer = getCustomer();

        $.post("/orders/getOrdersByCustomer", {customerID: customer.customerID}, function (orderList) {
            console.log("Previous orders are printing... ");

            let output =
                "<h1>Order history</h1>" +
                "</br>" +
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>OrderID</th>" +
                "<th>Order date</th>" +
                "<th>Total price</th>" +
                "<th>Number of items</th>" +
                "<th>Press to see content</th>" +
                "</tr>";

            let line = 1;
            $.each(orderList, function( key, order) {
                let orderDate = new Date(order.orderDate).toLocaleDateString('no-NO');
                    output +=
                        "<tr>" +
                        "<td><input type='text' disabled readonly id='orderID"+line+"' size='3' value='"+order.orderID+"'/></td>" +
                        "<td>" + orderDate + "</td>" +
                        "<td>" + order.totalPrice + " NOK" + "</td>" +
                        "<td>" + order.amount + "</td>" +
                        "<td><a class='btn btn-success' onclick='getOrderContent("+line+")'>See order content</button></td>" +
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
            "<h3>Order #"+orderID+"</h3>" +
            "</br>" +
            "<table class='table table-striped table-bordered'>" +
            "<tr>" +
            "<th>ProductID</th>" +
            "<th>Product name</th>" +
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
                "<td>" + orderContentList[i].price + " NOK" + "</td>" +
                "</tr>";
        }
        output += "</table>";
        $("#orderContentHistory").html(output);
    });

}