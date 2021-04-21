package portfolio2.packages.Objects;

import java.util.ArrayList;

public class ProductRegister {
    private static ArrayList<Product> productRegister = new ArrayList<>();

    public static ArrayList<Product> getProductRegister() {
        return productRegister;
    }

    public static void addProduct(Product product){
        productRegister.add(product);
    }

    //Removes object from productRegister (not a required function)
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
}