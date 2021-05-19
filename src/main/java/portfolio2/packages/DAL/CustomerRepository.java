package portfolio2.packages.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio2.packages.Exceptions.InvalidCustomerException;
import portfolio2.packages.Objects.Customer;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    public JdbcTemplate db;

    public String addCustomer(Customer customer) {
        String sql;
        try {
            customer.setNewCustomerID();
            sql = "INSERT INTO Customer (customerID, firstname, lastname, address, zipcode, telephone, email, password) VALUES (?,?,?,?,?,?,?,?)";

            db.update(sql, customer.getCustomerID(), customer.getFirstname(), customer.getLastname(), customer.getAddress(),
                    customer.getZipcode(), customer.getTelephone(), customer.getEmail(), customer.getPassword());
        } catch (Exception e) {
            System.out.println("Customer repository has an exception: " + e);
            return "Something went wrong trying to add customer.";
        }
        return "OK";
    }

    public Customer getCustomerByID(String customerID) throws InvalidCustomerException {
        System.out.println(customerID);
        if(customerID == null){
            return null;
        }
        try{
            String sql = "SELECT customerID, firstname, lastname, address, Customer.zipcode, city, telephone, email FROM Customer " +
                    "INNER JOIN City ON Customer.zipcode = City.zipcode " +
                    "WHERE customerID = ?";
            List<Customer> customers = db.query(sql, new BeanPropertyRowMapper<>(Customer.class), customerID);

            if(customers.size() == 0){
                throw new InvalidCustomerException("There is no such customer in the database. The customerID-cookie has been tampered with: ");
            }
            
            return customers.get(0);
        }catch(InvalidCustomerException e){
            System.out.println(e);
            return null;
        }
    }

    // getting the customers, but not showing the passwords as a privacy-concern
    public List<Customer> getCustomers() {
        try {
            String sql = "SELECT customerID, firstname, lastname, address, Customer.zipcode, city, telephone, email FROM Customer " +
                    "INNER JOIN City ON Customer.zipcode = City.zipcode " +
                    "ORDER BY customerID";
            return db.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Customer checkIfValidCustomerLogin(String email, String password){
        try{
            String sql = "SELECT customerID, email FROM Customer WHERE email = ? AND password = ?";
            List<Customer> loggedOnCustomers = db.query(sql, new BeanPropertyRowMapper<>(Customer.class), email, password);

            if(loggedOnCustomers.size()>0) {
                return loggedOnCustomers.get(0);
            }
            else{return null;}

        }catch(Exception e){
            return null;
        }


    }

    public List<String> checkZipcode(String zipcode){
        try {
            String sql = "SELECT zipcode FROM City " +
                    "WHERE zipcode = ?";
            return db.query(sql,new BeanPropertyRowMapper<>(String.class), zipcode);
        } catch (Exception e) {
            return null;
        }
    }
}
