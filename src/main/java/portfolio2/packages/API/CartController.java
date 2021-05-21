package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.Cart;
import portfolio2.packages.Objects.Carts;
import portfolio2.packages.Objects.Product;
import java.util.List;

@RestController
@EnableScheduling
public class CartController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getNumberOfCartItems")
    public int getNumberOfCartItems(String customerID) {
        try{
            Cart cart = Carts.getCart(customerID);
            if (cart == null) {
                return 0;
            }
            return cart.getProductsInCart().size();
        } catch(NullPointerException e){
            System.out.println("Cannot get the number of cart items: " + e);
            return 0;
        }

    }

    @GetMapping("/getTotalPrice")
    public double getTotalPrice(String customerID) {
        try {
            Cart cart = Carts.getCart(customerID);
            if (cart == null) {
                return 0;
            }
            return cart.getTotalPrice();
        }
        catch (NullPointerException e){
            System.out.println("Cannot get the totalprice: " + e);
            return 0.0;
        }
    }

    @GetMapping("/getCartItems")
    public List<Product> getAllCartItems(String customerID) {
        try {
            Cart cart = Carts.getCart(customerID);
            if (cart == null) {
                return null;
            }
            return cart.getProductsInCart();
        } catch(NullPointerException e){
            System.out.println("Cannot get the totalprice: " + e);
            return null;
        }
    }

    @PostMapping("/addToCart")
    public String addToCart(String customerID, Integer productID){
        try {
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
                return newCart.addProductToCart(product) + ". New cart was added to customer. Customers list has now " + newCart.getProductsInCart().size() + " products in it.";
            }
            //Else, add product to their already registered cart
            return cart.addProductToCart(product) + ". Customers list has " + cart.getProductsInCart().size() + " products in it.";
        } catch(Exception e){
            System.out.println("POST-call not supported from browserviewing: " + e);
            return "POST-call not supported from browserviewing";
        }
    }

    @PostMapping("/emptyCart")
    public String emptyCart(String customerID) {
        try {
            Cart cart = Carts.getCart(customerID);
            assert cart != null;
            cart.getProductsInCart().clear();
            return "Cart belonging to customer " + customerID + " is now empty.";
        } catch(NullPointerException e){
            System.out.println("POST-call not supported from browserviewing: " + e);
            return "POST-call not supported from browserviewing";
        }
    }


    @PostMapping("/deleteFromCart")
    public String deleteFromCart(String productID, String customerID) {
        try {
            if (productID == null) {
                return "Product is not in database";
            } else if (customerID == null) {
                return "Not valid customerID";
            } else {
                int pID = Integer.parseInt(productID);
                Cart cart = Carts.getCart(customerID);
                assert cart != null;
                return cart.removeProductFromCart(pID);
            }
        } catch(NullPointerException e){
            System.out.println("POST-call not supported from browserviewing: " + e);
            return "POST-call not supported from browserviewing";
        }
    }


}