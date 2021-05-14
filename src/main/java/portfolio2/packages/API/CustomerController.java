package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CustomerRepository;
import portfolio2.packages.Objects.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@EnableScheduling
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
    public Customer logOnCustomer(String email, String password, String tempUserID){
        if( email.isEmpty()      || email.isBlank()     ||
            password.isBlank()   || password.isEmpty()  ||
            tempUserID.isEmpty() || tempUserID.isBlank() ){
            return null;
        }

        else {
            System.out.println("linje 44 i logOnCustomer i Controller");

            Customer customer = repository.getLoggedInCustomer(email, password);
            if (customer == null) {
                return null;
            } else {
                mergeTempUser(customer, tempUserID);
                return customer;
            }
        }
    }

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        System.out.println("Get customers - kommer inn i controllermetoden");
        return repository.getCustomers();
    }

    @PostMapping("/addCustomer")
    public String addCustomer(Customer customer){
        if(customer == null){
            return "Could not add customer (customer is null)";
        }
        repository.addCustomer(customer);
        return "Customer added succsessfully";
    }


    @PostMapping("/logOutCustomer")
    public void logOutCustomer(String email){

    }


    // **** Helper methods ****
    private void mergeTempUser(Customer customer, String tempUserID){
        if(tempUserID.isBlank() || tempUserID.isEmpty()){
            return;
        }

        Cart tempCart = Carts.getCart(tempUserID);
        if(tempCart == null || tempCart.getProductsInCart().size() == 0) {
            return;
        }

        //Get or create cart for customer
        Cart customerCart = Carts.getCart(customer.getCustomerID());

        if(customerCart == null){
            customerCart = Carts.addCart(customer.getCustomerID());
        }

        //Add products from the temporary cart to customers cart
        for(Product product : tempCart.getProductsInCart()){
            customerCart.addProductToCart(product);
        }

        //Remove temporary cart from list of carts after products
        //has been moved to customers cart after logging in
        Carts.deleteCart(tempUserID);
    }


    @PostMapping("/checkZipcode")
    public String checkZipcode(String zipcode){
        List<String> list = repository.checkZipcode(zipcode);
        if(list.size()>0){
            return "OK";
        }
        else{return "Fail";}
    }



}
