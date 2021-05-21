"use strict";
$(function() {
    deleteCookie("orderID");
    // getting all products
    viewProducts();


});


    function viewProducts(){
        $.get("products/getProducts", function (products) {
            let adminProductcardElement = document.getElementById("adminProductContainer");
            console.log("All products: ", products);
            adminProductcardElement.innerHTML = formatProductData(products);
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
                            "<button class='btn btn-primary' id='deleteproduct' onclick='deleteProduct("+product.productID.toString()+")' value='" + product.productID + "'>Delete product</button>" +
                        "</div>" +
                    "</div>" +
                "</div>"   +

                /*Modal for change product*/
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
                                    "<li class='edit-product__line'><label for='productID"+product.productID+"''>ID</label><input type='text' readonly id='productID"+product.productID+"' value='"+product.productID+"' disabled/></li>" +
                                    "<li class='edit-product__line'><label for='productName"+product.productID+"''>Name</label><input type='text' id='productName"+product.productID+"' value='"+product.productName+"'/></li>" +
                                    "<li class='edit-product__line'><label for='shortDescription"+product.productID+"'>Short description</label><input type='text' id='shortDescription"+product.productID+"' value='"+product.shortDescription+"'/></li>" +
                                    "<li class='edit-product__line'><label for='longDescription"+product.productID+"'>Long description</label><input type='text' id='longDescription"+product.productID+"' value='"+product.longDescription+"'/></li>" +
                                    "<li class='edit-product__line'><label for='price"+product.price+"'>Price</label><input type='text' id='price"+product.productID+"' value='"+product.price+"'/></li>" +
                                    "<li class='edit-product__line'><label for='imageURL"+product.imageURL+"'>ImageURL</label><input type='text' id='imageURL"+product.productID+"' value='"+product.imageURL+"' disabled/></li>" +
                                "</ul>" +
                                "<div id='inputalert"+product.productID+"'></div>" +
                            "</div>" +
                            "<div class=\"modal-footer\">" +
                                "<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" id='closingbutton"+product.productID+"'>Close</button>" +
                                "<button type=\"button\" class=\"btn btn-primary\" onclick='changeProduct("+product.productID+")'>Save changes</button>" +
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

        let productNameElement      = document.getElementById("productName"+productID);
        let shortDescriptionElement = document.getElementById("shortDescription"+productID);
        let longDescriptionElement  = document.getElementById("longDescription"+productID);
        let priceElement            = document.getElementById("price"+productID);

        if( isValid(productNameElement, "Name","inputalert"+productID) &&
            isValid(shortDescriptionElement, "Short description", "inputalert"+productID) &&
            isValid(longDescriptionElement, "Long description", "inputalert"+productID) &&
            isValidNumber(priceElement, "Price", "inputalert"+productID)) {

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
                console.log("Resultat fra å endre produkt: " + result);
                viewProducts();
                let closemodal = document.getElementById("closingbutton"+productID);
                closemodal.click();
            }else{
                console.log("Resultat fra å endre produkt: ", result);
            }
        })
    }else{
        }
    }

    // delete the selected line
    function deleteProduct(productID){
        $.post("/products/deleteProduct", {productID: productID}, function(result){
            if(result === "OK. Product deleted."){
                viewProducts();
            }else{
                console.log("Resultat av å slette produkt: " + result);
            }
        })

    }

    function logOutAdmin(){
        deleteCookie("adminpassword");
        deleteCookie("adminusername");
        window.location.href = "index.html";
    }

    //Add new product
    function addProduct(){
        let productNameElement      = document.getElementById("productName")
        let shortDescriptionElement = document.getElementById("shortDescription")
        let longDescriptionElement  = document.getElementById("longDescription")
        let priceElement            = document.getElementById("price")
        let imageURLElement         = document.getElementById("imageURL")

        if( isValid(productNameElement, "Name","inputalert") &&
            isValid(shortDescriptionElement, "Short description","inputalert") &&
            isValid(longDescriptionElement, "Long description","inputalert") &&
            isValidNumber(priceElement, "Price","inputalert")) {

            const newProduct = {
                productName      : productNameElement.value,
                shortDescription : shortDescriptionElement.value,
                longDescription  : longDescriptionElement.value,
                price            : priceElement.value,
                imageURL         : imageURLElement.value,
            };
            $.post("products/addProduct", newProduct, function(result){
                if(result !== "Product added!"){
                    console.log("Could not add product (product is null)")
                }else{
                    viewProducts();
                    let closemodal = document.getElementById("closingbutton");
                    closemodal.click();
                    clearInputfields();
                }
            })
            }else{
            //wrong, do not post anything
        }
    }

    function isValidNumber(inputElement, elementName, errorMessageId){
        if(/\S/.test(inputElement.value) && !isNaN(inputElement.value)){
            // string is not empty, is a number and not just whitespace
            $("#"+errorMessageId).fadeOut();
            inputElement.classList.remove("input-alert");
            //valid
            return true;
        }else{
            $("#"+errorMessageId).fadeIn();
            inputElement.classList.add("input-alert");
            $("#"+errorMessageId).html('Field "' + elementName + '" does not have a number in it. Please write a price with numbers.');
            return false;
        }
    }

    function isValid(inputElement,elementName, errorMessageId){
        console.log("InputElement: ", inputElement);
        if(inputElement === null){
            return  false;
        }

        if(inputElement.value.length > 0 && /\S/.test(inputElement.value)){
            $("#"+errorMessageId).fadeOut();
            inputElement.classList.remove("input-alert");
            //valid
            return true;
        }else{
            $("#"+errorMessageId).fadeIn();
            inputElement.classList.add("input-alert");
            $("#"+errorMessageId).html('Field "' + elementName + '" is not filled in.');
            return false;
        }
    }

    function clearInputfields(){
        $("#productName").val('')
        $("#shortDescription").val('')
        $("#longDescription").val('')
        $("#price").val('')
        $("#imageURL").val('')
    }


