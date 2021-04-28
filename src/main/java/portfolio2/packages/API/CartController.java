package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CartRepository;
import portfolio2.packages.Objects.Product;

import java.util.ArrayList;

// kan ikke ha requestbody når vi skal bruke et post-kall
@RestController

public class CartController {

    @Autowired
    CartRepository repository;

    @GetMapping("/cart/")
    public int getNumberOfCartItems(){
        return 1;
    }

    @GetMapping("/cart/allItems")
    public ArrayList<Product> getAllCartItems(){
        return null;
    }

    @GetMapping("/cart/addToCart/{productID}")
    public boolean addToCart(@PathVariable int productID){
        return false;
    }

}
