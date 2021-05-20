package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Exceptions.InvalidProductException;
import portfolio2.packages.Objects.Cart;
import portfolio2.packages.Objects.Carts;
import portfolio2.packages.Objects.Product;

import java.util.List;

// kan ikke ha requestbody når vi skal bruke et post-kall
@RestController
@EnableScheduling
public class CartController {

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

    @GetMapping("/getTotalPrice")
    public double getTotalPrice(String customerID) {
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            return 0;
        }
        return cart.getTotalPrice();
    }

    @GetMapping("/getCartItems")
    public List<Product> getAllCartItems(String customerID) {
        Cart cart = Carts.getCart(customerID);
        if (cart == null) {
            return null;
        }
        return cart.getProductsInCart();

    }

    @PostMapping("/addToCart")
    public String addToCart(String customerID, Integer productID) throws InvalidProductException {
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

    @PostMapping("/emptyCart")
    public String emptyCart(String customerID) {
        Cart cart = Carts.getCart(customerID);
        assert cart != null;
        cart.getProductsInCart().clear();
        return "Cart belonging to customer " + customerID + " is now empty.";
    }


    @PostMapping("/deleteFromCart")
    public String deleteFromCart(String productID, String customerID) {
        if (productID == null) {
            return "Product is not in database";
        } else if (customerID == null) {
            return "Not valid customerID";
        } else {
            int pID = Integer.parseInt(productID);
            Cart cart = Carts.getCart(customerID);
            assert cart != null;
            cart.removeProductFromCart(pID);
            return "OK";
        }
    }


}