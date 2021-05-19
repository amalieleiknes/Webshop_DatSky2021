package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.*;
import portfolio2.packages.Validator.AdminValidator;

import java.util.List;

@RestController
@EnableScheduling
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

    @PostMapping("/deleteProduct")
    public String deleteProduct(Integer productID){
        System.out.println("ProductID er: " + productID);
        if(productID == null){
            return "No productID was found.";
        }
       return repository.deleteProduct(productID);
    }

    @PostMapping("/changeProduct")
    public String changeProduct(Product product){
        if(product == null){
            return "Product is null.";
        }
        if(getProductByID(product.getProductID()) == null){
            return "Can't find product in database.";
        }
        return repository.changeProductByID(product);
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return repository.getProducts();
    }

    @GetMapping("/{productID}/getProduct")
    public Product getProductByID(@PathVariable int productID){
        return repository.getProductByID(productID);
    }

    @GetMapping("/checkAdmin")
    public boolean checkAdminLogon(String username, String password){
        return AdminValidator.validateAdmin(username, password);
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product){
        if(product == null){
            return "Could not add product (product is null)";
        }
        Product newProduct = new Product(product.getProductName(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice(),
                product.getImageURL());
        return repository.addProduct(newProduct);
    }



}
