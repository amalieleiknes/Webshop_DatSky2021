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
        try {
            return UUID.randomUUID().toString();
        } catch(Exception e){
            System.out.println("Could not generate orderID: " + e);
            return "Could not generate orderID";
        }
    }

    // getting a order, based on order ID
    @GetMapping("/{orderID}/getOrder")
    public Order getOrderByID(@PathVariable String orderID){
        try {
            if (orderID.isBlank() || orderID.isEmpty()) {
                return null;
            }
            return repository.getOrderByID(orderID);
        } catch(Exception e){
            System.out.println("Could not get order by ID " + e);
            return null;
        }
    }

    // getting all the orders
    @GetMapping("/getOrders")
    public List<Order> getAllOrders(){
        try{
        return repository.getAllOrders();
        } catch(Exception e){
            System.out.println("Could not get all orders: " + e);
            return null;
        }
    }

    // adding one order to the database
    @PostMapping("/addOrder")
    public String addOrder(Order order){
        try{
        if(order == null){
            return "Could not add order (order is null)";
        }
        Order newOrder = new Order(order.getOrderID(), order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
        return repository.addOrder(newOrder);
        } catch(Exception e){
            System.out.println("Could not add order: " + e);
            return "Could not add order because POST is not showed from webbrowser";
        }
    }

    @PostMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(String customerID) {
        try {
            if (customerID.equals("0")) {
                throw new InvalidCustomerException("The customer {0} does not exist. Allowing this call will result in a selection of all Orders in the database");
            } else {
                return repository.getOrdersByCustomer(customerID);
            }
        } catch(InvalidCustomerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/addOrdercontent")
    public String addOrdercontent(String orderID, String customerID){
        try {
            CartController cart = new CartController();
            List<Product> listOfProducts = cart.getAllCartItems(customerID);

            if (listOfProducts == null) {
                return "Could not add Ordercontent (Ordercontent is null)";
            } else {
                return repository.addOrdercontent(orderID, listOfProducts);
            }
        } catch(Exception e){
            System.out.println("POST not available in browser: " + e.getMessage());
            return null;
        }

    }

    @PostMapping("/getOrdercontent")
    public List<OrderLine> getOrderContent(String orderID) {
        try {
            return repository.getOrdercontent(orderID);
        } catch (Exception e) {
            System.out.println("POST not available in browser: " + e.getMessage());
            return null;
        }
    }

}
