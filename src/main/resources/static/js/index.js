"use strict";
$(function() {
    getProducts();
    getNumberOfCartItems();
    //When user is not logged in, generate a temporary userID
    //so user can add products to their cart.
    let user = getCustomer();
    let tempUserID = getCookie("tempUserID");
    console.log("TempUserID :", tempUserID);
    console.log("CustomerID :", user.customerID);

    if(user.customerID === "" && tempUserID === ""){
        let tempUserID = Math.random().toString(36).substring(7);
        setCookie("tempUserID", tempUserID, 1);
        let cookie = getCookie("tempUserID");
        console.log("CookieTemp: ", cookie);
    }
});

    function addToCart(productID){
        let user = getCustomer();
        //If user is not logged in (just visiting website and want to add product to cart),
        //get their temporary userID and add to cart attached to their ID.
        if(user.customerID === null || user.customerID.length === 0){
            let tempUserId = getCookie("tempUserID");
            console.log("TempUserID", tempUserId);
            $.post("/addToCart", {customerID : tempUserId, productID : productID}, function(result){
                getNumberOfCartItems();
                console.log(result);
            });
        }else{
            $.post("/addToCart", {customerID : user.customerID, productID: productID}, function(result){
                getNumberOfCartItems();
                console.log(result);
            });
        }
    }


    // function to print out all prducts on the startpage
    function getProducts() {
        $.get("products/getProducts/", function (allProducts) {

            let productCardElement = document.getElementById("card-container");

            $.each(allProducts, function (counter, product) {
                const card = document.createElement("div");
                card.classList.add('card-body');

                let cardContent  =
                    "<div class='card' id='" + product.productID + "'>" +
                        "<div class='card-body' style='padding: 10px;'" +
                            "<h5 class='card-title' id='productNameIndex'>" + product.productName + "</h5>" +
                            "</br>" +
                            "<img class='card-img' src= '" + product.imageURL + "' alt='img of a product' width='250' height='auto'/>" +
                            "</br>" +
                            "<p class='card-description'>" + product.shortDescription + "</p>" +
                            "<p class='card-price'>" + product.price + " NOK</p>" +
                            "<div class='button-wrapper'>" +
                                "<a id='goToProduct' href='productpage.html?productID=" + product.productID + "' class='btn btn-primary'>Go to product</a>" +
                                "<button class='btn btn-primary' id='buttonaddcart' onclick='addToCart("+product.productID+")' value='" + product.productID + "'>Add to cart</button>" +
                            "</div>" +
                        "</div>" +
                    "</div>";
                productCardElement.innerHTML += cardContent;
            });
        });
    }//End of ready
