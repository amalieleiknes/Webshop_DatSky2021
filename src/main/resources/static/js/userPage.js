$(function(){
    getOrderHistory();


    function getOrderHistory() {
        let customer = getCustomer();

        $.post("/order/getOrdersByCustomer", {customerID: customer.customerID}, function (orderList) {
            let output =
                "<h1>Order history</h1>" +
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>OrderID</th>" +
                "<th>Order Date</th>" +
                "<th>Totalprice</th>" +
                "<th>Number of items</th>" +
                "</tr>";

            console.log(orderList.length);

            let i = 0;
            for(; i<orderList.length;i++) {
                console.log("Previous orders are printing... ");
                output +=
                    "<tr>" +
                    "<td>" + orderList[i].orderID + "</td>" +
                    "<td>" + orderList[i].orderDate + "</td>" +
                    "<td>" + orderList[i].totalPrice + "</td>" +
                    "<td>" + orderList[i].amount + "</td>" +
                    "<td><a class='btn btn-success' onclick='getOrderContent(" + orderList[i].orderID + ")'>Save</button></td>" +
                    "</tr>";
                output += "</table>";
            }
            $("#orderHistory").html(output);
        });
    }
});


function getOrderContent(orderID){
    $.post("/order/getOrdercontent", {orderID: orderID} ,function(orderContentList){
        let output =
            "<h1>Content of order marked</h1>" +
            "<table class='table table-striped table-bordered'>" +
            "<tr>" +
            "<th>ProductID</th>" +
            "<th>Product Name</th>" +
            "<th>Price</th>" +
            "</tr>";

        let i;
        for (i=0; i<orderContentList.length; i++){
            console.log("Previous orders are printing... ");
            console.log(orderContentList[i].price);
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