package portfolio2.packages.Objects;

import java.util.ArrayList;

public class OrderRegister {
    private static ArrayList<Order> orderlist = new ArrayList<>();

    public static ArrayList<Order> getOrderlist() {
        return orderlist;
    }

    public static void addOrder(Order order){
        orderlist.add(order);
    }

    public static String generateOrderID(){
        String orderID = "order";
        return orderID;
    }
}
