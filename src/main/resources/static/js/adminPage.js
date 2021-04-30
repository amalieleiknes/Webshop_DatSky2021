"use strict";
$(function() {
    // getting all products
    $.get("products/getProducts", function (products) {
        console.log("All products: ", products);
        formatProductData(products)
    });
});

    // function to format the table
    function formatProductData(products){
        let table=
            "<table class='table table-condensed'>" +
                "<thead>" +
                    "<tr>" +
                    "<th>Name</th>" +
                    "<th>Short Description</th>" +
                    "<th>Long Description</th>" +
                    "<th>Price</th>" +
                    "<th>image</th>" +
                    "<th>Change and Save</th>" +
                    "<th>Delete</th>" +
                    "<th>Copy</th>" +
                "</thead>";
        let line=1;
        $.each(products, function( key, product) {
            table+=
                "<tr>" +
                    "<td><input type='text' readonly id='productID"+line+"' size='10' value='"+product.productID+"'/></td>" +
                    "<td><input type='text' id='name"+line+"' value='"+product.productName+"'/></td>" +
                    "<td><input type='text' id='shortDescription"+line+"' value='"+product.shortDescription+"'/></td>" +
                    "<td><input type='text' id='longDescription"+line+"' value='"+product.longDescription+"'/></td>" +
                    "<td><input type='text' id='price"+line+"' value='"+product.price+"'/></td>"+
                    "<td><input type='text' readonly id='image"+line+"' size=12 value='"+product.imageURL+"'/></td>" +
                    "<td><a class='btn btn-success' onclick='changeProduct("+line+")'>Save</button></td>" +
                    "<td><a class='btn btn-danger' onclick='deleteProduct("+line+")'>Delete</button></td>" +
                    "<td><a class='' onclick='copyProduct("+line+")'>Copy</button></td>" +
                "</tr>";
            line++;
        });
        table +="</tbody></table>";
        return table;
    }
    

    // function to save changes to your product
    function changeProduct(line){


    }

    // delete the selected line
    function deleteProduct(line){

    }

    // copying a line that will be added with a new productnumber at the end of the line
    function copyProduct(line){

    }

    // adding a new blank line that can be edited
    function addProduct(){

    }


