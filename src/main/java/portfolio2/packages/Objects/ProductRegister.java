package portfolio2.packages.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductRegister {

    static Product product1;
    static Product product2;
    static Product product3;

    public static void initialize() {
        product1 = new Product(1, "norsk kaffe", "en norsk pose med kaffi",
                "en stor pose med lokal kaffi", 35.00, "images/kaffe.jpg");

        product2 = new Product(2, "svensk kaffe", "en svensk pose med kaffi",
                "en stor pose med kaffi", 15.00, "images/kaffe.jpg");

        product3 = new Product(3, "brasiliansk kaffe", "en pose med god kaffi",
                "en stor pose med digg kaffi", 85.00, "images/kaffe.jpg");

        productRegister.add(product1);
        productRegister.add(product2);
        productRegister.add(product3);
    }

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
