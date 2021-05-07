package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.OrderRepository;
import portfolio2.packages.Objects.Order;

import java.util.List;

@RestController
@Service
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository repository;
/*

    @GetMapping("/{orderID}")
    public Order getOrderByID(@PathVariable String orderID){
        return OrderRegister.getOrderByID(orderID);
    }
*/

    @GetMapping("/getOrderByID")
    public Order getOrderByID(String orderID){
        if(orderID.isBlank() || orderID.isEmpty()){
            return null;
        }
        return repository.getOrderByID(orderID);
    }

    @GetMapping("/getOrders")
    public List<Order> getAllOrders(){
        return repository.getOrders();
    }

    @PostMapping("/addOrder")
    public String addOrder(Order order){
        if(order == null){
            return "Could not add order (order is null)";
        }
        return repository.addOrder(order);
    }


    // TODO: må fikse denne
    @GetMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(){
        return repository.getOrders();
    }




}
