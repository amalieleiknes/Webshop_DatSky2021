$(function(){
    let orderID = getCookie("orderID");
    $.get("/orders/"+orderID+"/getOrder", function(order){
        let orderDate = new Date(order.orderDate).toLocaleDateString('no-NO');

        $("#confOrderDateValue").empty().html(orderDate);
        $("#confOrderAmountValue").empty().html(order.amount);
        $("#confOrderTotalPriceValue").empty().html(order.totalPrice + " NOK");
        console.log("OrderID: " + orderID + ", orderDate: " + orderDate + ", amount: " + order.amount + ", totalprice: " + order.totalPrice);
    });

    $.post("/orders/getOrdercontent", {orderID: orderID} ,function(orderContentList){
        let output =
            "<h4>Order #"+orderID+"</h4>" +
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
        $("#itemsInOrder").empty().html(output);
    });
});
