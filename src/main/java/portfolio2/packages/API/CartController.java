package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.Product;
import portfolio2.packages.Objects.ShoppingCart;

import java.util.ArrayList;

// kan ikke ha requestbody når vi skal bruke et post-kall
@RestController

public class CartController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/cart/")
    public int getNumberOfCartItems(){
        return ShoppingCart.getNumberOfProductsInCart();
    }

    @GetMapping("/cart/allItems")
    public ArrayList<Product> getAllCartItems(){
        return ShoppingCart.getProductList();
    }

    @GetMapping("/cart/addToCart/{productID}")
    public boolean addToCart(@PathVariable int productID){
        for(Product p : repository.getProducts()){
            if(productID == p.getProductID()){
                return ShoppingCart.addProductToShoppingCart(p);
            }
        }
        return false;
    }
}
