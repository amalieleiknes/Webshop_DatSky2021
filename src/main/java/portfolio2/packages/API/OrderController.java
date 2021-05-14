package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.OrderRepository;
import portfolio2.packages.Objects.Cart;
import portfolio2.packages.Objects.Order;
import portfolio2.packages.Objects.OrderContent;
import portfolio2.packages.Objects.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@EnableScheduling
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
        Order newOrder = new Order(order.getOrderID(), order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
        return repository.addOrder(newOrder);
    }

    @PostMapping("/addOrderContent")
    public String addOrdercontent(OrderContent ordercontent) {
        System.out.println("OrderController - addOrdercontent: Orderconent.getOrderID(): " + ordercontent.getOrderID());
        List<Product> orderContentList = new ArrayList<>();
        System.out.println(ordercontent.getOrderProductList());
        for(Product p : ordercontent.getOrderProductList()){
            System.out.println("Order content: Product name: " + p.getProductName());
            orderContentList.add(p);
        }
        if (orderContentList.size() == 0) {
            return "Order content is null";
        }
        OrderContent newOrdercontent = new OrderContent(ordercontent.getOrderID(), orderContentList);
        return repository.addOrdercontent(newOrdercontent);
    }


    // TODO: må fikse denne
    @GetMapping("/getOrdersByCustomer")
    public List<Order> getOrdersByCustomer(){
        return repository.getOrders();
    }

    @GetMapping("/generateOrderID")
    public String generateOrderID(){
        String orderID = UUID.randomUUID().toString();
        return orderID;
    }


}
