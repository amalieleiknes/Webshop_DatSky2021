package portfolio2.packages.Objects;

import java.util.ArrayList;

public class OrderRegister {
    private static ArrayList<Order> orderlist = new ArrayList<>();

    public static ArrayList<Order> getOrderlist() {
        return orderlist;
    }
}
