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
});
