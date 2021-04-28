package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CustomerRepository;
import portfolio2.packages.Objects.Customer;
import portfolio2.packages.Objects.Order;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

/*
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
*/

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        return repository.getCustomers();
    }

    @PostMapping("/addCustomer")
    public String addCustomer(Customer customer){
        if(customer == null){
            return "Could not add customer (customer is null)";
        }
        repository.addCustomer(customer);
        return "Customer added succsessfull";
    }


    @PostMapping("/logOutCustomer")
    public void logOutCustomer(String email){

    }


}
