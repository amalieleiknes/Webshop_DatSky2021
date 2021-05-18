$(function(){
    getOrderHistory();


    function getOrderHistory() {
        let customer = getCustomer();

        $.post("/order/getOrdersByCustomer", {customerID: customer.customerID}, function (orderList) {
            console.log(orderList.length);
            let i = 0;
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

            for(; i<orderList.length;i++) {
                output +=
                    "<tr>" +
                    "<td>" + orderList[i].orderID + "</td>" +
                    "<td>" + orderList[i].orderDate + "</td>" +
                    "<td>" + orderList[i].totalPrice + "</td>" +
                    "<td>" + orderList[i].amount + "</td>" +
                    "<td><a class='btn btn-success' onclick='getOrderContent("+orderList[i].orderID+")'>See ordercontent</button></td>" +
                    //"<td><button class='btn btn-success' id='btnGetOrderContent"+i+"' onclick='getOrderContent("+orderList[i].orderID+")'" +
                    //" value='" + orderList[i].orderID + "'>See ordercontent</button></td>" +
                    "</tr>";

                console.log(orderList[i].orderID);
            }
            output += "</table>";

            $("#orderHistory").empty().html(output);
        });
    }
});


function getOrderContent(orderID){
    console.log("Going into getOrderContent");
    $.post("/order/getOrdercontent", {orderID: orderID} ,function(orderContentList){
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