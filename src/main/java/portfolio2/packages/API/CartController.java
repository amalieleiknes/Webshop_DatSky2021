package portfolio2.packages.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio2.packages.Objects.Product;
import portfolio2.packages.Objects.ShoppingCart;

import java.util.ArrayList;


@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/")
    public int getNumberOfCartItems(){
        return ShoppingCart.getNumberOfProductsInCart();
    }

    @GetMapping("/allItems")
    public ArrayList<Product> getAllCartItems(){
        return ShoppingCart.getProductList();
    }


}
