package portfolio2.packages.API;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio2.packages.Objects.Customer;
import portfolio2.packages.Objects.CustomerRegister;
import portfolio2.packages.Objects.Order;


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



}
