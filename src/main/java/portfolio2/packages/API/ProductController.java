package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.ProductRepository;
import portfolio2.packages.Objects.*;
import java.util.List;

@RestController
@EnableScheduling
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return repository.getProducts();
    }

    @GetMapping("/{productID}/getProduct")
    public Product getProductByID(@PathVariable String productID){
        String pID = productID.replace("{","");
        pID = pID.replace("}","");
        int finalProductID = Integer.parseInt(pID);

        return repository.getProductByID(finalProductID);
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

    @PostMapping("/changeProduct")
    public String changeProduct(Product product){
        if(product == null){
            return "Product is null.";
        }
        if(repository.getProductByID(product.getProductID()) == null){
            return "Can't find product in database.";
        }
        return repository.changeProductByID(product);
    }

}
