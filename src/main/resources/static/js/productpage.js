"use strict";
$(window).on('load', function(){

    let urlObject = new URL(window.location.href);
    let productID = urlObject.searchParams.get('productID');

    $.get("/products/{"+productID+"}/getProduct", function(product){

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
                    "<img class='card-img' src='" + product.imageURL + "' alt='img of a product' width='300px' height='400px'/>" +
                    "</br></br>" +
                    "<h4>Description of the product:</h4>" +
                    "<p class='card-description'>" + product.longDescription + "</p>" +
                    "</br>" +
                    "<p class='card-price'>Price in NOK: " + product.price + ",-</p>" +
                "<button class='btn btn-primary' id='buttonaddcart' onclick='addToCart("+product.productID+")' " +
                "value='" + product.productID + "'>Add to cart</button>" +
                "</div>"
        }
        else{
            content = "<p> This product does not exist</p>"
        }

        productcontent.innerHTML = content;

    });
    
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

function blabla() {

}
