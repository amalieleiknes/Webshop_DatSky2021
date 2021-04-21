package portfolio2.packages.Objects;

import java.util.ArrayList;

public class OrderContent {
    private static ArrayList<Product> orderProductList = new ArrayList<>();

    public static ArrayList<Product> getOrderProductList() {
        return orderProductList;
    }
}
