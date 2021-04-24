package portfolio2.packages.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio2.packages.Objects.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{productID}")
    public Product getProductByID(@PathVariable int productID){
        return ProductRegister.getProductByID(productID);
    }

    @GetMapping("/getShoppingcart")
    public ArrayList<Product> getShoppingcart(){
        return ShoppingCart.getProductList();
    }

    @GetMapping("/")
    public ArrayList<Product> getAllProducts(){
        return ProductRegister.getProductRegister();
    }


}
