package portfolio2.packages.Objects;

import java.util.ArrayList;
import java.util.List;

public class Carts {
    public static List<Cart> carts = new ArrayList<>();

    public static List<Cart> getCarts(){
        return carts;
    }

    public static Cart getCart(String customerID) {
        if(customerID.isEmpty() || customerID.isBlank()){
            return null;
        }
        for (Cart cart : carts) {
            if (cart.getOwner().equals(customerID)) {
                return cart;
            }
        }
        return null;
    }

    public static Cart addCart(String customerID){
        Cart cart = new Cart(customerID);
        carts.add(cart);
        return cart;
    }

    public static void deleteCart(String owner){
        for(int i = 0; i < carts.size(); i++){
            if(carts.get(i).getOwner().equals(owner)){
                carts.remove(i);
                return;
            }
        }
    }
}
