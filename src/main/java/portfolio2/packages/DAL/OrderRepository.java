package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Exceptions.InvalidOrderException;
import portfolio2.packages.Exceptions.InvalidProductException;
import portfolio2.packages.Objects.Order;
import portfolio2.packages.Objects.OrderLine;
import portfolio2.packages.Objects.Product;

import java.util.ArrayList;
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

            return db.query(sql, new BeanPropertyRowMapper<>(Order.class));
        }catch(Exception e){
            return null;
        }
    }

    // getting the orders where customerID in order-table is the same as given customerID
    public List<Order> getOrdersByCustomer(String customerID) {
        try{
            String sql =    "SELECT * FROM `Order` " +
                            "WHERE customerID = ?";

            List<Order> orders = db.query(sql, new BeanPropertyRowMapper<>(Order.class), customerID);
            return orders;
        } catch(Exception e){
            System.out.println("getOrdersByCustomer - catch: " + e.getMessage());
            return null;
        }
    }

    // getting one order based on orderID
    public Order getOrderByID(String orderID){
        String sql = "SELECT * FROM `Order` WHERE orderID = ?";
        try{
            List<Order> orders = db.query(sql, new BeanPropertyRowMapper<>(Order.class), orderID);

            if(orders.size() == 0){
                throw new InvalidOrderException("OrderID does not exist.");
            }

            return orders.get(0);
        } catch(InvalidOrderException e){
            System.out.println(e.getMessage());
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

    public String addOrdercontent(String orderID, List<Product> listOfProducts) {
        if (listOfProducts.size() == 0) {
            return "Could not add order content, order content is null";
        } else {
            String sql;

            // formatting the Ordercontent in a new list
            List<OrderLine> orderContent = new ArrayList<>();
            for(Product p : listOfProducts){
                OrderLine aLine = new OrderLine(orderID, p.getProductID(),
                        p.getProductName(), p.getPrice());

                orderContent.add(aLine);
            }
                try {
                    for (OrderLine orderline : orderContent) {
                        System.out.println("addOrderContent - orderline: " + orderline);
                        sql = "INSERT INTO Ordercontent (orderID, productID, productName, price) VALUES (?,?,?,?)";
                        db.update(sql, orderID, orderline.getProductID(), orderline.getProductName(), orderline.getPrice());
                    }
                    return "Order content added!";
                } catch (Exception e) {
                    return "Could not add order content. Exception: " + e.getMessage();
                }
        }
    }



    public List<OrderLine> getOrdercontent (String orderID){
        String sql;
        try{
            sql = "SELECT * FROM Ordercontent WHERE orderID = ?";
            return db.query(sql,new BeanPropertyRowMapper<>(OrderLine.class), orderID);
        }catch(Exception e){
            System.out.println("getOrdercontent: Catch: " + e.getMessage());
            return null;
        }
    }


    // TODO: kan dette slettes?
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
