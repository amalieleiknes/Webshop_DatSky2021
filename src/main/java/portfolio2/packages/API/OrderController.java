package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.OrderRepository;
import portfolio2.packages.Objects.Order;

import java.util.List;
import java.util.UUID;

@RestController
@EnableScheduling
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository repository;

/* TODO: Kan vi bare slette dette?

    @GetMapping("/{orderID}")
    public Order getOrderByID(@PathVariable String orderID){
        return OrderRegister.getOrderByID(orderID);
    }
*/

    // getting a order, based on order ID
    @GetMapping("/getOrderByID")
    public Order getOrderByID(String orderID){
        if(orderID.isBlank() || orderID.isEmpty()){
            return null;
        }
        return repository.getOrderByID(orderID);
    }

    // getting all the orders ever made
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return repository.getAllOrders();
    }

    // adding one order to the database
    @PostMapping("/addOrder")
    public String addOrder(Order order){
        if(order == null){
            return "Could not add order (order is null)";
        }
        return repository.addOrder(order);
    }

    //getting an order based on customer ID
    @GetMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(String customerID){
        return repository.getCustomersOrders(customerID);
    }

    // generating an order ID
    @GetMapping("/generateOrderID")
    public String generateOrderID(){
        //return repository.generateOrderID();
        return UUID.randomUUID().toString();
    }


}
