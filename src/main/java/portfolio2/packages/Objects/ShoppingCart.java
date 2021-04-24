package portfolio2.packages.Objects;

import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<Product> shoppingcartList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return shoppingcartList;
    }

    public static boolean addProductToShoppingCart(int productID){
        boolean added = false;
        for(Product p : ProductRegister.getProductRegister()){
            if(productID == p.getProductID()){
                shoppingcartList.add(p);
                added = true;
            }
        }
        return added;
    }

    public static void deleteProductFromShoppingCart(Product product){
        if(product == null){
            return;
        }
        for(Product p : shoppingcartList){
            if(product.equals(p)){
                shoppingcartList.remove(product);
            }
        }
    }

    public static double getTotalPrice(){
        double total = 0.0;
        for(Product p : shoppingcartList){
            total += p.getPrice();
        }
        return total;
    }

    public static int getNumberOfProductsInCart(){
        return shoppingcartList.size();
    }
}
