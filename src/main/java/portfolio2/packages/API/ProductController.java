package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

 /*   @GetMapping("/{productID}")
    public Product getProductByID(@PathVariable int productID){
        return ProductRegister.getProductByID(productID);
    }
*/
/*    @GetMapping("/getShoppingcart")
    public ArrayList<Product> getShoppingcart(){
        return ShoppingCart.getProductList();
    }*/

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return repository.getProducts();
    }

    @GetMapping("/getProductByID")
    public Product getProductByID(String productID){
        if(productID == null){
            return null;
        }
        return repository.getProductByID(productID);
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product){
        if(product == null){
            return "Could not add product (product is null)";
        }
        return repository.addProduct(product);
    }


}
