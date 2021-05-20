package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.OrderRepository;
import portfolio2.packages.Exceptions.InvalidCustomerException;
import portfolio2.packages.Objects.*;
import java.util.List;
import java.util.UUID;

@RestController
@EnableScheduling
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository repository;

    // generating an order ID
    @GetMapping("/generateOrderID")
    public String generateOrderID(){
        return UUID.randomUUID().toString();
    }

    // getting a order, based on order ID
    @GetMapping("/{orderID}/getOrder")
    public Order getOrderByID(@PathVariable String orderID){
        if(orderID.isBlank() || orderID.isEmpty()){
            return null;
        }
        return repository.getOrderByID(orderID);
    }

    // getting all the orders
    @GetMapping("/getOrders")
    public List<Order> getAllOrders(){
        return repository.getAllOrders();
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

    @PostMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(String customerID) throws InvalidCustomerException {
        if(customerID.equals("0")){
            throw new InvalidCustomerException("The customer {0} does not exist. Allowing this call will result in a selection of all Orders in the database");
        }
        else {
            return repository.getOrdersByCustomer(customerID);
        }
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

    @PostMapping("/getOrdercontent")
    public List<OrderLine> getOrderContent(String orderID){
        return repository.getOrdercontent(orderID);
    }

}
