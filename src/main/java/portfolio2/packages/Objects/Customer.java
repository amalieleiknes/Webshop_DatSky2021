package portfolio2.packages.Objects;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class Customer{

    private String customerID;
    private String firstname;
    private String lastname;
    private String address;
    private String postnumber;
    private String postoffice;
    private String tlphNumber;
    private String email;
    private String password;

    //POJO
    public Customer(){}

    public Customer(String firstname, String lastname, String address, String postnumber, String postoffice,
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
    }

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

    public String getPostnumber() {
        return postnumber;
    }

    public void setPostnumber(String postnumber) {
        this.postnumber = postnumber;
    }

    public String getPostoffice() {
        return postoffice;
    }

    public void setPostoffice(String postoffice) {
        this.postoffice = postoffice;
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
}
