"use strict";
$(function(){
    getAllProducts();
    getNumberofItemsInCart();

    // function to print out all prducts on the startpage
    function getAllProducts() {
        $.get("/products/", function(allProducts){
            let productCardElement = document.getElementById("card-container");

            $.each(allProducts, function(counter, product) {

                const card = document.createElement("div");
                card.classList.add('card-body');

                let cardContent =
                    "<div class='card' id='" + product.productID + "' style='width:30%; padding: 5px; display: inline-block; margin: 1.5%;' >" +
                    "<div class='card-body' style='padding: 10px;'" +
                    "<h5 class='card-title'>" + product.name + "</h5>" +
                    "</br>" +
                    "<img class='card-img' src='" + product.imageUrl + "' alt='img of a product' width='250' height='250'/>" +
                    "</br>" +
                    "<p class='card-description'>" + product.shortDescription + "</p>" +
                    "</br>" +
                    "<p class='card-price'>" + product.price + "</p>" +
                    "<a id='goToProduct' href='productpage.html?productID=" + product.productID + "' class='btn btn-primary'>Go to product</a>" +
                    "<button id='addToCart'>Add to cart</button>" +
                    "</div>" +
                    "</div>";

                productCardElement.innerHTML += cardContent;

            });
        });
    }

    // TODO: finne produktID til produktet tilhørende knappen og sende det inn i java så produktet legges til i array
    // adding product to cart if button is pressed
    $("#addToCart").click(function(){
        $.post("/cart/addToCart", function(){

        });
        getNumberofItemsInCart();
    });


    // function to print out all prducts on the startpage
    function getNumberofItemsInCart() {
        $.get("/cart/", function(numberOfProducts){
            let element = document.getElementById("numberOfCartItems");

            element.innerHTML = "<div class='card'>" +
                "<p>" + numberOfProducts + "</p>" +
                "</div>";

        });
    }

});