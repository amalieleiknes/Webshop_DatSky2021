package portfolio2.packages.API;

import org.springframework.web.bind.annotation.*;
import portfolio2.packages.Objects.Product;
import portfolio2.packages.Objects.ShoppingCart;

import java.util.ArrayList;

// kan ikke ha requestbody når vi skal bruke et post-kall
@RestController
public class CartController {

    @GetMapping("/cart/")
    public int getNumberOfCartItems(){
        return ShoppingCart.getNumberOfProductsInCart();
    }

    @GetMapping("/cart/allItems")
    public ArrayList<Product> getAllCartItems(){
        return ShoppingCart.getProductList();
    }

    @PostMapping("/addToCart/{productID}")
    public boolean addToCart(@PathVariable int productID){
        return ShoppingCart.addProductToShoppingCart(productID);
    }

}
