package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CartRepository;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.Cart;
import portfolio2.packages.Objects.Carts;
import portfolio2.packages.Objects.Product;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// kan ikke ha requestbody når vi skal bruke et post-kall
@RestController

public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/getNumberOfCartItems")
    public int getNumberOfCartItems(String customerID) {
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            return 0;
        }
        return cart.getProductsInCart().size();
    }
    @GetMapping("/getCartItems")
    public List<Product> getAllCartItems(String customerID) {
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            return null;
        }
        return cart.getProductsInCart();

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

    @PostMapping("/addToCart")
    public String addToCart(String customerID, String productID) {
        if (customerID.isEmpty() || customerID.isBlank()) {
            return "Not valid customerID";
        }
        if (productID == null) {
            return "Product is not i database";
        }
        Product product = productRepository.getProductByID(productID);
        //Checks if a customer has a cart registered
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            //If customer doesn't have a cart, generate a cart and add product to this cart
            Cart newCart = Carts.addCart(customerID);
            newCart.addProductToCart(product);
            return "New cart was added to customer. Customers list has now " + newCart.getProductsInCart().size() + " products in it.";
        }
        //Else, add product to their already registered cart
        cart.addProductToCart(product);
        return "Customers list has " + cart.getProductsInCart().size() + " products in it.";
    }
}

