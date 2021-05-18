package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Cart;
import portfolio2.packages.Objects.Product;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate db;

    public String addPurchaseToDatabase(Cart cart, String customerID){
        String sql;
        try {
            for (Product product : cart.getProductsInCart()) {
                sql = "INSERT INTO Purchase (customerID, productID) VALUES (?,?)";
                db.update(sql, customerID, product.getProductID());
            }
        }catch(Exception e){
            return "Could not add successfull purchase to database";
        }
        return "Purchase added to database";
    }
}
