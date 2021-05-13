package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Objects.Customer;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    public JdbcTemplate db;

    public String addCustomer(Customer customer) {
        String sql;
        int postoffice;
        //TODO: Kan sjekke opp mot postnummer og adresse senere.
   /*     try {
            sql = "SELECT count(*) FROM City WHERE zipcode=?";
            City = db.queryForObject(sql, Integer.class, customer.getZipcode());
        } catch (Exception e) {
            return "Could not connect to database, failed adding customer.";
        }
        if (postoffice == 0) {
            //If postoffice not in database, add this postoffice
            try {
                sql = "INSERT INTO City (Postnumber, Postoffice) VALUES (?,?)";
                db.update(sql, customer.getPostnumber(), customer.getPostoffice());
            } catch (Exception e) {
                return "Could not add customer with their postoffice";
            }
        }*/

        // TODO: Noe er feil i repositoriet her... Men hva?
        try {
            sql = "INSERT INTO Customer (firstname, lastname, address, zipcode, tlfnumber, email, password) VALUES (?,?,?,?,?,?,?)";
            db.update(sql, customer.getFirstname(), customer.getLastname(), customer.getAddress(),
                    customer.getZipcode(), customer.getTlphNumber(), customer.getEmail(), customer.getPassword());
        } catch (Exception e) {
            System.out.println("Feil i add customer i repository");
            return "Something went wrong trying to add customer.";
        }
        System.out.println("Add customer i repository fungerer");
        return "OK";
    }

    public Customer getLoggedInCustomer(String email, String password){
        if(email.isBlank() || email.isEmpty() || password.isEmpty() || password.isBlank()){
            return null;
        }
        try{
            String sql = "SELECT * FROM Customer WHERE email = ? AND password = ?";
            List<Customer> loggedOnCustomers = db.query(sql, new BeanPropertyRowMapper<>(Customer.class), email, password);
            return loggedOnCustomers.get(0);
        }catch(Exception e){
            return null;
        }
    }

    public Customer getCustomerByID(String customerID){
        if(customerID == null){
            return null;
        }
        try{
            String sql = "SELECT * FROM Customer WHERE customerID = ?";
            List<Customer> customers = db.query(sql, new BeanPropertyRowMapper<>(Customer.class));
            return customers.get(0);
        }catch(Exception e){
            return null;
        }
    }

    // getting the customers, but not showing the passwords as a privacy-concern
    public List<Customer> getCustomers() {
        try {
            String sql = "SELECT customerID, firstname, lastname, address, Customer.zipcode, city, tlfnumber, email FROM Customer " +
                    "INNER JOIN City ON Customer.zipcode = City.zipcode " +
                    "ORDER BY customerID";
            return db.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        } catch (Exception e) {
            return null;
        }
    }

    public String getCity(String zipcode){
        //try {
            //String sql = "SELECT city FROM City " +
                    //"WHERE ? = City.zipcode";
            // TODO: String city = db.query(sql, zipcode);
            // return city;
        //} catch (Exception e) {
            return null;
        //}
    }
}
