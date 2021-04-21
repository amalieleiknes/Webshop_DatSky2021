package portfolio2.packages.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderRegister {
    @Autowired
    private static ArrayList<Order> orderlist = new ArrayList<>();

    @Autowired
    public static ArrayList<Order> getOrderlist() {
        return orderlist;
    }

    @Autowired
    public static void addOrder(Order order){
        orderlist.add(order);
    }

    @Autowired
    public static Order getOrderByID(String orderID){
        Order ret = null;
        for(Order o : orderlist){
            if(o.getOrderID() == orderID){
                ret = o;
            }
        }
        return ret;
    }

    @Autowired
    public static String generateOrderID(){
        String orderID = "order";
        return orderID;
    }
}
