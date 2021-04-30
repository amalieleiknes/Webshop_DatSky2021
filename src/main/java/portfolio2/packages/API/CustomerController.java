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
    @GetMapping(value = "/{customerID}/orders", produces = { "application/hal+json"})
    public CollectionModel<Order> getaCustomersOrders(@PathVariable final String customerID){
        //List<Order> orders =
        return null;
    }
*/

    @GetMapping("/{customerID}")
    public Customer getCustomerByID(@PathVariable String customerID){
        return repository.getCustomerByID(customerID);
    }


    @PostMapping("/logOnCustomer")
    public Customer logOnCustomer(String email, String password){
        if(email.isEmpty() || email.isBlank() || password.isBlank() || password.isEmpty()){
            return null;
        }
        Customer customer = repository.getLoggedInCustomer(email, password);
        if(customer == null){
            return null;
        }
        return customer;
    }

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
