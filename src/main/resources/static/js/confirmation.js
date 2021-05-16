$(function(){
    let orderID = getCookie("orderID");
    $.get("/order/getOrderByID", {orderID: orderID}, function(order){
        let orderDate = new Date(order.orderDate).toLocaleDateString('no-NO');

        $("#confOrderIDValue").empty().html(orderID);
        $("#confOrderDateValue").empty().html(orderDate);
        $("#confOrderAmountValue").empty().html(order.amount);
        $("#confOrderTotalPriceValue").empty().html(order.totalPrice + " NOK");
        console.log("OrderID: " + orderID + ", orderDate: " + orderDate + ", amount: " + order.amount + ", totalprice: " + order.totalPrice);
    });

    $.get("/order/getOrdercontent", {orderID: orderID}, function(ordercontent){
        console.log("Ordercontent: ", ordercontent);
        console.log("Ordercontent.length: ", ordercontent.length);
        let output =
            "<table class='table table-striped table-bordered'>" +
            "<tr>" +
            "<th>Product</th>" +
            "<th>Price</th>" +
            "</tr>";



        for (const product of ordercontent){
            console.log("Products in ordercontent: ", product);
            output +=
                "<tr>" +
                "<td>" + product.productName + "</td>" +
                "<td>" + product.price + " NOK" + "</td>" +
                "</tr>";
        }
        output += "</table>";
        $("#itemsInOrder").empty().html(output);
    });
});
