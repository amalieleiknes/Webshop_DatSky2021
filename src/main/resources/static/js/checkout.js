$(function(){
    let customer = getCustomer();
    let tempUserID = getCookie("tempUserID");
    let todaysDate = new Date().toJSON().slice(0,10).replace(/-/g,'/');

    //TODO: generere ordreID
    //TODO: få opp bekreftelse på ordre med all ordreinformasjon
    $("#registerOrderBtn").click(function(){
        //If user on website is not logged in, check for products in temporary cart
        if(customer.customerID === null || customer.customerID.length === 0){
            $.get("/getCartItems", {customerID : tempUserID} ,function(products){
                const newOrder = {
                    orderID: 100,
                    orderDate: todaysDate,
                    totalPrice: 0,
                    amount: products.length,
                    customerID: tempUserID,
                    productsInCart: products,
                };
                console.log("Order: " + newOrder.orderID + ", " + newOrder.amount + ", " + newOrder.productsInCart);
                $.post("/addOrder", newOrder);
            });

            //If user is logged in, get products from customers cart
        }else{
            $.get("/getCartItems", {customerID : customer.customerID} ,function(products){
                const newOrder = {
                    orderID: 100,
                    orderDate: todaysDate,
                    totalPrice: 0,
                    amount: products.length,
                    customerID: customer.customerID,
                    productsInCart: products,
                };
                console.log("Order: " + newOrder.orderID + ", " + newOrder.amount + ", " + newOrder.productsInCart);
                $.post("/addOrder", newOrder);
            });
        }
    });
});