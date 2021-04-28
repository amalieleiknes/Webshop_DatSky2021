"use strict";
$(window).on('load', function(){

    let urlObject = new URL(window.location.href);
    let productID = urlObject.searchParams.get('productID');

    $.get("/products/getProductByID", {productID: productID}, function(product){

        // printing out the one product that is chosen
        let productcontent = document.getElementById("productcontent");
        let shoppingcartcontent = document.getElementById("productpageShoppingcart");

        //TODO: finne ut om productID faktisk finnes slik at if-en nedenfor kan brukes (feks gjennom et GETkall)

        // getting the content of the product
        let content;
        if(product.name!=="") {
            console.log("Inne i formater-produktinfo", product);
            content =
                "<div class='product' style='width:80%; padding: 5px; display: inline-block; margin: 1.5%;'>" +
                    "<h2 class='card-title'>" + product.productName + "</h2>" +
                    "</br>" +
                    "<img class='card-img' src='" + product.imageURL + "' alt='img of a product' width='300px' height='300px'/>" +
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



        // TODO: lage et sammendrag av handlekurven på høyre side på produktsiden
        //shoppingcartcontent.innerHTML = cartcontent;

    });
});