package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CustomerRepository;
import portfolio2.packages.Objects.*;
import java.util.List;


@RestController
@EnableScheduling
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    // getting one customer by ID
    @GetMapping("/{customerID}")
    public Customer getCustomerByID(@PathVariable String customerID){
        return repository.getCustomerByID(customerID);
    }

    // getting all customers
    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        return repository.getCustomers();
    }

    @PostMapping("/addCustomer")
    public String addCustomer(Customer customer){
        if(customer == null){
            return "FAIL";
        }
        repository.addCustomer(customer);
        return "OK";
    }

    @PostMapping("/checkIfValidCustomerLoginInfo")
    public String checkIfValidCustomerLoginInfo(String email, String password, String tempUserID){
        Customer customer = repository.checkIfValidCustomerLogin(email, password);
        if (customer == null) {
            return "FAIL";
        } else {
            mergeTempUser(customer, tempUserID);
            return customer.getCustomerID();
        }
    }

    // TODO: hva er denne til?
    @PostMapping("/logOutCustomer")
    public void logOutCustomer(String email){

    }

    @PostMapping("/checkZipcode")
    public String checkZipcode(Customer customer){
        String zipcode = customer.getZipcode();
        List<String> list = repository.checkZipcode(zipcode);

        if(list.size()>0){
            return "OK";
        }
        else{
            return "Fail";
        }
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
}
