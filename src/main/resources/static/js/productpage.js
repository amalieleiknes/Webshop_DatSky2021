"use strict";
$(window).on('load', function(){

    let urlObject = new URL(window.location.href);
    let productID = urlObject.searchParams.get('productID');

    console.log(productID);

    $.get("/products/" + productID, function(product){


        // printing out the one product that is chosen
        let productcontent = document.getElementById("productcontent");

        let content;

        if(product.name!=="") {
            content =
                "<div class='product' style='width:80%; padding: 5px; display: inline-block; margin: 1.5%;'>" +
                    "<h2 class='card-title'>" + product.name + "</h2>" +
                    "</br>" +
                    "<img class='card-img' src='" + product.imageUrl + "' alt='img of a product' width='300px' height='300px'/>" +
                    "</br>" +
                    "<p>Description of the product:</p>" +
                    "</br>" +
                    "<p class='card-description'>" + product.longDescription + "</p>" +
                    "</br>" +
                    "<p class='card-price'>Price in NOK: " + product.price + ",-</p>" +
                "</div>"
        }
        else{
            content = "<p> This product does not exist</p>"
        }

        productcontent.innerHTML = content;
        });

});