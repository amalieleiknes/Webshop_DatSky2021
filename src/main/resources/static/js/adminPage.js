"use strict";
$(function() {
    // getting all products
    viewProducts();


});


    function viewProducts(){
        $.get("products/getProducts", function (products) {
            let adminProductcardElement = document.getElementById("adminProductContainer");
            console.log("All products: ", products);
            let table = formatProductData(products);
            adminProductcardElement.innerHTML = table;
        });
    }

    // function to format the table
    function formatProductData(products){
        let content = "";
        $.each(products, function(counter, product){
            const card = document.createElement("div");
            card.classList.add('card-body');
            let productID = "productId"+product.productID.toString();
            let cardContent =
                "<div class='card' id='" + product.productID + "'>" +
                    "<div class='card-body' style='padding: 10px;'" +
                        "<h5 class='card-title'>" + product.productName + "</h5>" +
                        "</br>" +
                        "<img class='card-img' src= '" + product.imageURL + "' alt='img of a product' width='250' height='auto'/>" +
                        "</br>" +
                        "<p class='card-description'>" + product.shortDescription + "</p>" +
                        "<p class='card-price'>" + product.price + ",-</p>" +
                        "<div class='button-wrapper'>" +
                            "<button class='btn btn-primary' data-toggle='modal' data-target='#"+ productID +"'>Edit</button>" +
                            "<button class='btn btn-primary' id='deleteproduct' onclick='deleteProduct("+product.productID+")' value='" + product.productID + "'>Delete product</button>" +
                        "</div>" +
                    "</div>" +
                "</div>"   +

                /*MODAL TEST*/
                "<div class='modal fade' id='"+ productID +"' tabindex='-1' role='dialog'>" +
                    "<div class='modal-dialog' role='document'>" +
                        "<div class='modal-content'>" +
                            "<div class=\"modal-header\">" +
                                "<h1>Change '"+product.productName+"'</h1>" +
                                "<h5 class=\"modal-title\" id=\"exampleModalLabel\"></h5>" +
                                "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">" +
                                "<span>&times;</span>" +
                                "</button>" +
                            "</div>"+
                            "<div class=\"modal-body\">" +
                                "<ul class='edit-product'>" +
                                    "<li class='edit-product__line'><label for='productID"+product.productID+"''>ID</label><input type='text' readonly id='productID"+product.productID+"' value='"+product.productID+"'/></li>" +
                                    "<li class='edit-product__line'><label for='productName"+product.productID+"''>Name</label><input type='text' id='productName"+product.productID+"' value='"+product.productName+"'/></li>" +
                                    "<li class='edit-product__line'><label for='shortDescription"+product.productID+"'>Short description</label><input type='text' id='shortDescription"+product.productID+"' value='"+product.shortDescription+"'/></li>" +
                                    "<li class='edit-product__line'><label for='longDescription"+product.productID+"'>Long description</label><input type='text' id='longDescription"+product.productID+"' value='"+product.longDescription+"'/></li>" +
                                    "<li class='edit-product__line'><label for='price"+product.price+"'>Price</label><input type='text' id='price"+product.productID+"' value='"+product.price+"'/></li>" +
                                    "<li class='edit-product__line'><label for='imageURL"+product.imageURL+"'>ImageURL</label><input type='text' id='imageURL"+product.productID+"' value='"+product.imageURL+"'/></li>" +
                                "</ul>" +
                            "</div>" +
                            "<div class=\"modal-footer\">" +
                                "<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>" +
                                "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick='changeProduct("+product.productID+")'>Save changes</button>" +
                            "</div>" +
                        "</div>"+
                    "</div>"+
                "</div>" ;
                content += cardContent;
        })
        return content;
    }


    // function to save changes to your product
    function changeProduct(productID){
        const newInformation = {
            productID        : $("#productID" + productID).val(),
            productName      : $("#productName" + productID).val(),
            shortDescription : $("#shortDescription" + productID).val(),
            longDescription  : $("#longDescription" + productID).val(),
            price            : $("#price" + productID).val(),
            imageURL         : $("#imageURL" + productID).val()
        };

    $.post("/products/changeProduct", newInformation, function(result){
        if(result === "Product updated!"){
            viewProducts();
        }else{
            console.log("Resultat fra å endre produkt: ", result);
        }
        })
    }

    // delete the selected line
    function deleteProduct(productID){
        $.post("/products/deleteProduct", productID, function(result){
            if(result === "OK. Product deleted!"){
                viewProducts();
            }else{
                console.log("Resultat av å slette produkt: " + result);
            }
        })

    }
/*
    //Add new line to add productinformation
    function newProductline(){
        $.get("products/getProducts", function (products) {
            const table = formatProductData(products, true);
            $("#modifyProducts").html(table);
        });
    }*/

    function logOutAdmin(){
        deleteCookie("adminpassword");
        deleteCookie("adminusername");
        window.location.href = "index.html";
    };


    //Add new product
    function addProduct(line){
        const newProduct = {
            productName      : $("#productName" + line).val(),
            shortDescription : $("#shortDescription" + line).val(),
            longDescription  : $("#longDescription" + line).val(),
            price            : $("#price" + line).val(),
            imageURL         : $("#imageURL" + line).val()
        };

        console.log("ImageURL: ",newProduct.imageURL);

        if(isNaN(newProduct.price)){
            console.log("Not accepted price-value");
            return;
        }

        if (newProduct.productName.length === 0 ||
            newProduct.longDescription.length === 0||
            newProduct.shortDescription.length === 0 ||
            newProduct.price.length === 0){
            console.log("Some fields are need to be filled in.");
        }else{
            $.post("products/addProduct", newProduct, function(result){
                if(result !== "Product added!"){
                    console.log("Could not add product (product is null)")
                }else{
                    viewProducts();
                }
                })
        }
    }


