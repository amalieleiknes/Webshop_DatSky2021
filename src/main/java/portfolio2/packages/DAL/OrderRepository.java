package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Order;
import portfolio2.packages.Objects.Product;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate db;

    // getting all orders made
    public List<Order> getAllOrders(){
        try{
            String sql =    "SELECT * FROM `Order` " +
                            "ORDER BY customerID";
            List<Order> orders = db.query(sql, new BeanPropertyRowMapper<>(Order.class));
            System.out.println("Getting all orders");
            return orders;
        }catch(Exception e){
            return null;
        }
    }

    // getting the orders where customerID in order-table is the same as given customerID
    public List<Order> getCustomersOrders(String customerID) {
        try{
            String sql =    "SELECT * FROM `Order`" +
                            "WHERE " + customerID + " = `Order`.customerID";

            List<Order> orders = db.query(sql, new BeanPropertyRowMapper<>(Order.class));
            System.out.println("Getting customers orders");
            return orders;
        } catch(Exception e){
            return null;
        }


    }

    // getting one order based on orderID
    public Order getOrderByID(String orderID){
        String sql;
        int orderFound;
        try{
            sql = "SELECT count(*) FROM `Order` WHERE orderID = ?";
            orderFound = db.queryForObject(sql, Integer.class, orderID);
            System.out.println("getOrderByID: orderFound = " + orderFound);
            if(orderFound == 0){
                System.out.println("getOrderByID: orderFound == 0, returnerer null");
                return null;
            }
            sql = "SELECT * FROM `Order` WHERE orderID = ?";
            return db.queryForObject(sql,new BeanPropertyRowMapper<>(Order.class), orderID);
        }catch(Exception e){
            System.out.println("getOrderByID: Catch: " + e.getMessage());
            return null;
        }
    }

    // adding one order to the database
    public String addOrder(Order order){
        String sql;
        if(order == null){
            return "Could not add order. (Order is null)";
        }
        try{
            sql = "INSERT INTO `Order` (orderID, orderDate, totalprice, amount, customerID) VALUES (?,?,?,?,?)";
            db.update(sql, order.getOrderID(), order.getOrderDate(), order.getTotalPrice(), order.getAmount(), order.getCustomerID());
            return "Order added!";
        }catch(Exception e){
            return "Something went wrong. Could not add order." + e.getMessage();
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
                sql = "SELECT count(*) FROM `Order` WHERE orderID = ?";
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
