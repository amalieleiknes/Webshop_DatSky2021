package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.OrderRepository;
import portfolio2.packages.Objects.*;
import java.util.List;
import java.util.UUID;

@RestController
@EnableScheduling
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository repository;

    // generating an order ID
    @GetMapping("/generateOrderID")
    public String generateOrderID(){
        return UUID.randomUUID().toString();
    }

    // adding one order to the database
    @PostMapping("/addOrder")
    public String addOrder(Order order){
        if(order == null){
            return "Could not add order (order is null)";
        }
        Order newOrder = new Order(order.getOrderID(), order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
        return repository.addOrder(newOrder);
    }

    // getting a order, based on order ID
    @GetMapping("/getOrderByID")
    public Order getOrderByID(String orderID){
        if(orderID.isBlank() || orderID.isEmpty()){
            return null;
        }
        return repository.getOrderByID(orderID);
    }

    // TODO: fungerer ikke
    // getting all the orders ever made
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        System.out.println("Tester ordercontroller - getallorders");
        return repository.getAllOrders();
    }


    @PostMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(String customerID){
        System.out.println("ordercontroller - getordersbycust");
        return repository.getOrdersByCustomer(customerID);
    }


    @PostMapping("/addOrdercontent")
    public String addOrdercontent(String orderID, String customerID){
        CartController cart = new CartController();
        List<Product> listOfProducts = cart.getAllCartItems(customerID);

        if(listOfProducts == null){
            return "Could not add ordercontent (ordercontent is null)";
        }
        else{
            return repository.addOrdercontent(orderID, listOfProducts);
        }
    }


    @GetMapping("/getOrdercontent")
    public OrderContent getOrderContent(String orderID){
        return repository.getOrdercontent(orderID);
    }

}
