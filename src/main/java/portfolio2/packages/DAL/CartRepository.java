package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Product;

import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate db;

    public String addToCart(List<Product> products){
        return null;
    }

    /*public getCartItems(){
        return null;
    }*/
}
