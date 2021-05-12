$(function(){
    let customer = getCustomer();
    let tempUserID = getCookie("tempUserID");

    //TODO: få opp bekreftelse på ordre med all ordreinformasjon
    $("#registerOrderBtn").click(function(){
        let todaysDate = new Date();
        $.get("/order/generateOrderID", function(orderID){
            console.log("OrderID: " + orderID);
            setCookie("orderID", orderID, 1);

            //If user on website is not logged in, check for products in temporary cart
            if(customer.customerID === null || customer.customerID.length === 0){
                $.get("/getCartItems", {customerID : tempUserID} ,function(products){
                    const newOrder = {
                        orderID: orderID,
                        orderDate: todaysDate,
                        totalPrice: 0,
                        amount: products.length,
                        customerID: tempUserID
                    };

                    console.log("Order: " + newOrder.orderID + ", " + newOrder.amount + ", " + newOrder.orderDate + ", " + newOrder.customerID);
                    $.post("/order/addOrder", newOrder, function(message){
                        console.log(message);
                    });
                });

            //If user is logged in, get products from customers cart
            }else{
                $.get("/getCartItems", {customerID : customer.customerID} ,function(products){
                    const newOrder = {
                        orderID: orderID,
                        orderDate: todaysDate,
                        totalPrice: 0,
                        amount: products.length,
                        customerID: customer.customerID
                    };

                    console.log("Order: " + newOrder.orderID + ", " + newOrder.amount + ", " + newOrder.orderDate + ", " + newOrder.customerID);
                    $.post("/order/addOrder", newOrder, function(message){
                        console.log(message);
                    });
                });
                window.location.href="confirmation.html";
            }
        });
    });
});