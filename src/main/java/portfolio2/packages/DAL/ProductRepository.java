package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Product;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate db;

    public String deleteProduct(Integer productID){
        if(productID == null){
            return "ProductID is null.";
        }
        try{
            String sql = "DELETE FROM Product WHERE productID = ?";
            db.update(sql, productID);
        }catch(Exception e){
            return "Could not delete selected product.";
        }
        return "OK. Product deleted.";
    }

    public String changeProductByID(Product product){
        String sql;
        int productFound;
        try{
            sql = "SELECT count(*) FROM Product WHERE productID = ?";
            productFound = db.queryForObject(sql, Integer.class, product.getProductID());
            if(productFound == 0){
                return "No product matching in database";
            }
            sql = "UPDATE Product SET productID = ?, productName = ?, shortDescription = ?, longDescription = ?, price = ?, imageURL = ? WHERE productID = ?";
            db.update(sql, product.getProductID(), product.getProductName(), product.getShortDescription(), product.getLongDescription(), product.getPrice(), product.getImageURL(), product.getProductID());
        }catch(Exception e){
            return "Could not update product.";
        }
        return "Product updated!";
    }

    public List<Product> getProducts(){
        try{
            String sql = "SELECT * FROM Product";
            List<Product> products = db.query(sql, new BeanPropertyRowMapper(Product.class));
            return products;
        }catch(Exception e){
            return null;
        }
    }

    public Product getProductByID(Integer productID){
        String sql;
        try{
            sql = "SELECT count(*) FROM Product WHERE productID = ?";
            int productsFound = db.queryForObject(sql, Integer.class, productID);
            if(productsFound == 0){
                return null;
            }

            sql = "SELECT * FROM Product WHERE productID = ?";
            return db.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productID);
        }catch(Exception e){
            return null;
        }
    }

    public String addProduct(Product product){
        try{
            String sql = "INSERT INTO Product (productName, shortDescription, longDescription, price, imageURL) VALUES (?,?,?,?,?)";
            db.update(sql, product.getProductName(), product.getShortDescription(), product.getLongDescription(), product.getPrice(), product.getImageURL());
        }catch(Exception e){
            return "Could not add new product";
        }
        return "Product added!";
    }


}
