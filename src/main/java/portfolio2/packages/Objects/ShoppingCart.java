//TODO: Slettes?
// Lar denne stå fordi vi kan ha en handlekurv som lagres i et Array,
// og når kunden har trykket på "fullfør betaling", så lagres Arrayet i databasen
// Dette for å unngå at databasen fylles opp med informasjon om kjøp som ikke er gjennomført

package portfolio2.packages.Objects;


import java.util.List;

public class ShoppingCart {

/*    private static ArrayList<Product> shoppingcartList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return shoppingcartList;
    }

    public static boolean addProductToShoppingCart(Product product){
        try{
            shoppingcartList.add(product);
            return true;
        }
        catch(Exception e){
            return false;
        }
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
    }*/
}


