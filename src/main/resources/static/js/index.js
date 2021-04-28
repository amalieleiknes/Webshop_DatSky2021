"use strict";
$(function() {
    getProducts();
    getNumberofItemsInCart();


    // function to print out all prducts on the startpage
    function getProducts() {
        $.get("products/getProducts/", function (allProducts) {
            console.log("Inne i getProducts");
            console.log("Produkter: ", allProducts);
            let productCardElement = document.getElementById("card-container");

            $.each(allProducts, function (counter, product) {
                const card = document.createElement("div");
                card.classList.add('card-body');

                let cardContent =
                    "</script>" +
                    "<div class='card' id='" + product.productID + "'style='width:30%; padding: 5px; display: inline-block; margin: 1.5%;' >" +
                    "<div class='card-body' style='padding: 10px;'" +
                    "<h5 class='card-title'>" + product.productName + "</h5>" +
                    "</br>" +
                    "<img class='card-img' src= '" + product.imageURL + "' alt='img of a product' width='250' height='250'/>" +
                    "</br>" +
                    "<p class='card-description'>" + product.shortDescription + "</p>" +
                    "</br>" +
                    "<p class='card-price'>" + product.price + "</p>" +
                    "<a id='goToProduct' href='productpage.html?productID=" + product.productID + "' class='btn btn-primary'>Go to product</a>" +
                    "</br>" +
                    "<button class='btn btn-primary' id='addToCart' onclick='addToCart(" + product.productID + ")' value='addToCart'>Add to cart</button>" +
                    "</div>" +
                    "</div>";
                productCardElement.innerHTML += cardContent;
            });
        });
    }
});