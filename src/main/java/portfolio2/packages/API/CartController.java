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
    @GetMapping("/getNumberOfCartItems/")
    public int getNumberOfCartItems(String customerID) {
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            return 0;
        }
        return cart.getProductsInCart().size();
    ProductRepository repository;
    
    }
    @GetMapping("/cart/allItems")
    public ArrayList<Product> getAllCartItems() {
        return null;
    }


    @PostMapping("/addPurchaseToDatabase")
    public String addPurchaseToDatabase(String customerID, Cart cart) {
        if (customerID.isBlank() || customerID.isEmpty()) {
            return "Not valid customerID (customerID is null)";
        }
        if (cart == null) {
            return "Not a valid cart (cart is null)";
        }
        cartRepository.addPurchaseToDatabase(cart, customerID);
        return "OK";
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
