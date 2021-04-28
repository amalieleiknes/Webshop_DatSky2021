package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Order;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate db;

    public List<Order> getOrders(){
        try{
            String sql =    "SELECT * FROM `Order` " +
                            "JOIN Customer ON Customers.CustomerID = Order.CustomerID " +
                            "ORDER BY CustomerID";
            List<Order> orders = db.query(sql, new BeanPropertyRowMapper<>(Order.class));
            return orders;
        }catch(Exception e){
            return null;
        }
    }

    public Order getOrderByID(String orderID){
        String sql;
        int orderFound;
        try{
            sql = "SELECT count(*) FROM Order WHERE OrderID = ?";
            orderFound = db.queryForObject(sql, Integer.class, orderID);
            if(orderFound == 0){
                return null;
            }
            sql = "SELECT * FROM Order WHERE OrderID = ?";
            return db.queryForObject(sql, Order.class, orderID);
        }catch(Exception e){
            return null;
        }
    }


    public String addOrder(Order order){
        if(order == null){
            return "Could not add order. (Order is null)";
        }
        try{
            String sql = "INSERT INTO Order (Date, Totalprice, Amount, CustomerID) VALUES (?,?,?,?)";
            db.update(sql, order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
            return "Order added!";
        }catch(Exception e){
            return "Something went wrong. Could not add order.";
        }
    }
}
