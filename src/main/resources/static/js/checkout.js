$(function(){
    let customer = getCustomer();
    let tempUserID = getCookie("tempUserID");
    let customerID = getCookie("customerID");
    let emailID = getCookie("email");

    $("#registerOrderBtn").click(function(){
        // make sure this is generated before adding order
        if(tempUserID==="" && customerID==="" || emailID==="") {
            deleteCookie("email");
            deleteCookie("customerID");
            let tempUserID = Math.random().toString(36).substring(7);
            setCookie("tempUserID", tempUserID, 1);
            let cookieTemp = getCookie("tempUserID");
            console.log("No cookie is set, making a new one and relocating: ", cookieTemp);
            location.reload();
        }

        else {
            let todaysDate = new Date();

            // generating Order and Ordercontent
            $.get("/order/generateOrderID", function (orderID) {
                console.log("OrderID: " + orderID);
                setCookie("orderID", orderID, 1);

                //If user on website is not logged in, check for products in temporary cart
                if (customer.customerID === null || customer.customerID.length === 0) {
                    $.get("/getCartItems", {customerID: tempUserID}, function (products) {
                        const newOrder = {
                            orderID: orderID,
                            orderDate: todaysDate,
                            totalPrice: 0,
                            amount: products.length,
                            customerID: tempUserID
                        };

                        $.post("/order/addOrder", newOrder, function (message) {
                            console.log(message);
                            if(message === "Order added!"){
                                $.post("/order/addOrdercontent", {orderID: orderID, customerID: tempUserID}, function (message) {
                                        console.log(message);
                                    //window.location.href="confirmation.html";
                                });
                            }
                        });
                    });
                }

                //If user is logged in, get products from customers cart
                else {
                    $.get("/getCartItems", {customerID: customer.customerID}, function (products) {
                        const newOrder = {
                            orderID: orderID,
                            orderDate: todaysDate,
                            totalPrice: 0,
                            amount: products.length,
                            customerID: customer.customerID
                        };

                        $.post("/order/addOrder", newOrder, function (message) {
                            console.log(message);
                            if (message === "Order added!") {
                                $.post("/order/addOrdercontent", {orderID: orderID, customerID: customer.customerID}, function (addOrderContentMessage) {
                                    console.log(addOrderContentMessage);
                                    //window.location.href="confirmation.html";
                                });
                            }
                        });
                    });
                }
            });
        }
    });
});