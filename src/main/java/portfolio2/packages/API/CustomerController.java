package portfolio2.packages.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import portfolio2.packages.DAL.CustomerRepository;
import portfolio2.packages.Objects.*;
import portfolio2.packages.Validator.CustomerValidator;
import java.util.List;


@RestController
@EnableScheduling
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    // getting one customer by ID
    @GetMapping("/{customerID}/getCustomer")
    public Customer getCustomerByID(@PathVariable String customerID) {
        try{
            return repository.getCustomerByID(customerID);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    // getting all customers
    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        try{
            return repository.getCustomers();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @PostMapping("/addCustomer")
    public String addCustomer(Customer customer){
        try {
            if (customer == null) {
                return "Customer is null";
            } else {

                // checking if the input is valid and that the email is not already in use
                boolean validEmail = CustomerValidator.validateEmail(customer.getEmail());
                boolean availableEmail = repository.checkAvailability(customer.getEmail());
                boolean validPassword = CustomerValidator.validatePassword(customer.getPassword());
                boolean validTelephone = CustomerValidator.validateTelephone(customer.getTelephone());

                if (!validEmail) {
                    return "Email is not valid";
                } else if (!availableEmail) {
                    return "Email is not available to use";
                } else if (!validPassword) {
                    return "Password is not valid";
                } else if (!validTelephone) {
                    return "Telephone is not valid";
                } else {
                    return repository.addCustomer(customer);
                }
            }
        } catch(Exception e){
            System.out.println("Could not add customer: " + e);
            return null;
        }
    }

    @PostMapping("/checkIfValidCustomerLoginInfo")
    public String checkIfValidCustomerLoginInfo(String email, String password, String tempUserID){
        try {
            Customer customer = repository.checkIfValidCustomerLogin(email, password);
            if (customer == null) {
                return "FAIL";
            } else {
                mergeTempUser(customer, tempUserID);
                return customer.getCustomerID();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/checkZipcode")
    public String checkZipcode(Customer customer){
        try {
            String zipcode = customer.getZipcode();
            List<String> list = repository.checkZipcode(zipcode);

            if (list.size() > 0) {
                return "OK";
            } else {
                return "Fail";
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            return "Could not check zipcode";
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