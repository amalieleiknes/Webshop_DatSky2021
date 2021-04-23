package portfolio2.packages.API;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.Objects.Customer;
import portfolio2.packages.Objects.CustomerRegister;
import portfolio2.packages.Objects.Order;

import java.util.ArrayList;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @GetMapping("/{customerID}")
    public Customer getCustomerByID(@PathVariable String customerID){
        return CustomerRegister.getCustomerByID(customerID);
    }


    @GetMapping(value = "/{customerID}/orders", produces = { "application/hal+json"})
    public CollectionModel<Order> getaCustomersOrders(@PathVariable final String customerID){
        //List<Order> orders =
        return null;
    }

    @PostMapping("/logOnCustomer")
    public Customer checkLogIn(String email, String password){
        Customer cust = null;
        ArrayList<Customer> customers = CustomerRegister.getCustomerRegister();

        for(Customer c : customers){
            if(c.getEmail().equals(email) && c.getPassword().equals(password)){
                cust = c;
            }
        }
        return cust;
    }

    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getAllCustomers(){
        return CustomerRegister.getCustomerRegister();
    }


    @PostMapping("/logOutCustomer")
    public void logOutCustomer(String email){

    }

}
