package portfolio2.packages.Objects;

import java.util.List;

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

    //A constructor for when a customer is get from the database
    public Customer(String customerID, String firstname, String lastname, String address, String zipcode, String city,
                    String tlphNumber, String email, String password) {
        this.customerID = customerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.tlphNumber = tlphNumber;
        this.email = email;
        this.password = password;
        this.cart = null;
    }

    //POJO
    public Customer(){}


// TODO: Må finne riktig konstruktør som skal brukes her?
/*    public Customer(String firstname, String lastname, String address, String postnumber, String postoffice,
                    String tlphNumber, String email, String password) {
        this.customerID = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.postnumber = postnumber;
        this.postoffice = postoffice;
        this.tlphNumber = tlphNumber;
        this.email = email;
        this.password = password;
    }*/

    public String getCustomerID() {
        return customerID;
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

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
