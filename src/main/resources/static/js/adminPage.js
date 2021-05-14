"use strict";
$(function() {
    // getting all products
    viewProducts();


});


    function viewProducts(){
        $.get("products/getProducts", function (products) {
            console.log("All products: ", products);
            const table = formatProductData(products);
            $("#modifyProducts").html(table);
        });
    }

    // function to format the table
    function formatProductData(products, appendNew){
        let table=
            "<table class='table table-condensed'>" +
                "<thead>" +
                    "<tr>" +
                    "<th>ProductID</th>" +
                    "<th>Name</th>" +
                    "<th>Short Description</th>" +
                    "<th>Long Description</th>" +
                    "<th>Price</th>" +
                    "<th>image</th>" +
                    "<th>Save changes</th>" +
                    "<th>Delete</th>" +
                "</thead>" +
            "<tbody>";
        let line=1;
        $.each(products, function( key, product) {
            table+=
                "<tr>" +
                    "<td><input type='text' readonly id='productID"+line+"' size='3' value='"+product.productID+"'/></td>" +
                    "<td><input type='text' id='productName"+line+"' value='"+product.productName+"'/></td>" +
                    "<td><input type='text' id='shortDescription"+line+"' value='"+product.shortDescription+"'/></td>" +
                    "<td><input type='text' id='longDescription"+line+"' value='"+product.longDescription+"'/></td>" +
                    "<td><input type='text' id='price"+line+"' value='"+product.price+"'/></td>"+
                    "<td><input type='text' readonly id='imageURL"+line+"' value='"+product.imageURL+"'/></td>" +
                    "<td><a class='btn btn-success' onclick='changeProduct("+line+")'>Save</button></td>" +
                    "<td><a class='btn btn-danger' onclick='deleteProduct("+line+")'>Delete</button></td>" +
                "</tr>";
            line++;
        });
        if(appendNew){
            table+=
                "<tr>" +
                "<td><input type='text' disabled readonly id='productID"+line+"' size='3' value=''/></td>" +
                "<td><input type='text' id='productName"+line+"' value=''/></td>" +
                "<td><input type='text' id='shortDescription"+line+"' value=''/></td>" +
                "<td><input type='text' id='longDescription"+line+"' value=''/></td>" +
                "<td><input type='text' id='price"+line+"' value=''/></td>"+
                "<td><input type='file' disabled id='imageURL"+line+"' size=12 /></td>" +
                "<td><a class='btn btn-success' onclick='addProduct("+line+")'>Add product</button></td>" +
                "</tr>";
            line++;
        }
        table +="</tbody></table>";
        return table;
    }


    // function to save changes to your product
    function changeProduct(line){
        const newInformation = {
            productID        : $("#productID" + line).val(),
            productName      : $("#productName" + line).val(),
            shortDescription : $("#shortDescription" + line).val(),
            longDescription  : $("#longDescription" + line).val(),
            price            : $("#price" + line).val(),
            imageURL         : $("#imageURL" + line).val()
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
    function deleteProduct(line){

    }

    //Add new line to add productinformation
    function newProductline(){
        $.get("products/getProducts", function (products) {
            const table = formatProductData(products, true);
            $("#modifyProducts").html(table);
        });
    }

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


