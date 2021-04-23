"use strict";
$(function(){
    getAllProducts();

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
                    "<a id='goToProduct' href='productpage.html?productID=" + product.productID + "' class='btn btn-primary'>Go to product</a>" +
                    "</div>" +
                    "</div>";

                productCardElement.innerHTML += cardContent;

            });
        });
    }


});