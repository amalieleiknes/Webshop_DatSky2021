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
        try {
            return repository.getProducts();
        } catch(Exception e){
            System.out.println("Could not get products: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{productID}/getProduct")
    public Product getProductByID(@PathVariable int productID){
        try {
            return repository.getProductByID(productID);
        } catch(Exception e) {
            System.out.println("Could not get product by ID: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/checkAdmin")
    public boolean checkAdminLogon(String username, String password){
        try {
            return AdminValidator.validateAdmin(username, password);
        } catch(Exception e){
            System.out.println("Could not validate admin");
            return false;
        }
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