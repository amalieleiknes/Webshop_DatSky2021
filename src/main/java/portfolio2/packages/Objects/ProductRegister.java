package portfolio2.packages.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductRegister {
    @Autowired
    private static ArrayList<Product> productRegister = new ArrayList<>();

    @Autowired
    public static ArrayList<Product> getProductRegister() {
        return productRegister;
    }

    @Autowired
    public static void addProduct(Product product){
        productRegister.add(product);
    }

    //Removes object from productRegister (not a required function)
    @Autowired
    public static void deleteProduct(Product product){
        if(product == null){
            return;
        }
        for(Product p : productRegister){
            if(product.equals(p)){
                productRegister.remove(product);
            }
        }
    }

    @Autowired
    public static Product getProductByID(int productID){
        Product ret = null;
        for(Product p : productRegister){
            if(p.getProductID() == productID){
                ret = p;
            }
        }
        return ret;
    }

    @Autowired
    public static int generateProductID(){
        int productID = 0;
        return productID;
    }
}