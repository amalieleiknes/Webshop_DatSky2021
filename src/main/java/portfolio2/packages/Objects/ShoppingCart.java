package portfolio2.packages.Objects;

import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<Product> productList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static void addProductToShoppingCart(Product product){
        productList.add(product);
    }

    public static void deleteProductFromShoppingCart(Product product){
        if(product == null){
            return;
        }
        for(Product p : productList){
            if(product.equals(p)){
                productList.remove(product);
            }
        }
    }
}
