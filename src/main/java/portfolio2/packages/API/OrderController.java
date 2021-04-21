package portfolio2.packages.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio2.packages.Objects.Order;
import portfolio2.packages.Objects.OrderRegister;

import java.util.ArrayList;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{orderID}")
    public Order getOrderByID(@PathVariable String orderID){
        return OrderRegister.getOrderByID(orderID);
    }

    @GetMapping("/")
    public ArrayList<Order> getAllOrders(){
        return OrderRegister.getOrderlist();
    }
}
