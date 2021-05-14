package portfolio2.packages.Objects;

import portfolio2.packages.API.CustomerController;
import portfolio2.packages.DAL.CustomerRepository;

import java.util.List;
import java.util.UUID;

public class Customer{
    private String customerID;
    private String firstname;
    private String lastname;
    private String address;
    private String zipcode;
    private String city;
    private String tlphNumber;
    private String email;
    private String password;
    private List<Product> cart;

    private CustomerController customercontroller;

    //A constructor for when a customer is get from the database
    public Customer(String firstname, String lastname, String address, String zipcode,
                    String tlphNumber, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.tlphNumber = tlphNumber;
        this.email = email;
        this.password = password;
    }


    //POJO
    public Customer(){}


// TODO: Må finne riktig konstruktør som skal brukes her?


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID() {
        this.customerID = UUID.randomUUID().toString();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        customercontroller.getCity(zipcode);
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTlphNumber() {
        return tlphNumber;
    }

    public void setTlphNumber(String tlphNumber) {
        this.tlphNumber = tlphNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
