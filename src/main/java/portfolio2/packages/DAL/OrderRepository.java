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
        String sql;
        if(order == null){
            return "Could not add order. (Order is null)";
        }
        try{
            sql = "INSERT INTO `Order` (OrderID, OrderDate, Totalprice, Amount, CustomerID) VALUES (?,?,?,?,?)";
            db.update(sql, order.getOrderID(), order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
            return "Order added!";
        }catch(Exception e){
            return "Something went wrong. Could not add order.";
        }
    }

    //Method that checks if an integer is used as orderID in the database, and returns the integer if not
    //Denne fungerer foreløpig ikke, klarer ikke å hente ut fra databasen virker det som, så kommenterer den ut og
    // bruker randomUUID i Controlleren i stedet
    /*public String generateOrderID(){
        for(int i = 1; i < 100; i++){
            String sql;
            int duplicateOrder;

            try{
                sql = "SELECT count(*) FROM Order WHERE OrderID = ?";
                duplicateOrder = db.queryForObject(sql, Integer.class, i);
            } catch(Exception e){
                return "Error: klarte ikke å hente fra databasen";
            }

            if(duplicateOrder == 0){
                return String.valueOf(i);
            }
        }
        return "Error: ute av løkken";
    }*/
}
