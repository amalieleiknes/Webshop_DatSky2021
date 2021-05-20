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

    // adding a customer to the database
    public String addCustomer(Customer customer) {
        String sql;
        try {
            if(customer == null){
                throw new InvalidCustomerException("Customer is null and can therefore not be added to the database");
            }
            customer.setNewCustomerID();
            sql = "INSERT INTO Customer (customerID, firstname, lastname, address, zipcode, telephone, email, password)" +
                    " VALUES (?,?,?,?,?,?,?,?)";

            db.update(sql, customer.getCustomerID(), customer.getFirstname(), customer.getLastname(), customer.getAddress(),
                    customer.getZipcode(), customer.getTelephone(), customer.getEmail(), customer.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return "OK";
    }

    public boolean checkAvailability(String email){
        System.out.println(email);
        String sql;
        try {
            sql = "SELECT count(*) FROM Customer WHERE email = ?";
            //TODO: fikse en exception for denne
            int customerFound = db.queryForObject(sql, Integer.class, email);
            return customerFound <= 0;
        } catch (Exception e) {
            return false;
        }
    }

    // getting a customer from the database based on their ID
    public Customer getCustomerByID(String customerID) {
        try{
        if (customerID == null){
            return null;
        }
        else if (customerID.equals("0")){
            throw new InvalidCustomerException("The customer {0} does not exist. Allowing this call will result in a selection of all Orders in the database");
        }
        else {
            String sql = "SELECT customerID, firstname, lastname, address, Customer.zipcode, city, telephone, email FROM Customer " +
                    "INNER JOIN City ON Customer.zipcode = City.zipcode " +
                    "WHERE customerID = ?";
            List<Customer> customers = db.query(sql, new BeanPropertyRowMapper<>(Customer.class), customerID);

            if (customers.size() == 0) {
                throw new InvalidCustomerException("There is no such customerID {" + customerID + "} in the database. " +
                        "The customerID-cookie has been tampered with.");
            }

            return customers.get(0);
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
