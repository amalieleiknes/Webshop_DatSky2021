package portfolio2.packages.Objects;

import java.util.ArrayList;

public class CustomerRegister {
    private static ArrayList<Customer> customerRegister = new ArrayList<>();

    public static ArrayList<Customer> getCustomerRegister() {
        return customerRegister;
    }

    public static void addCustomer(Customer customer){
        customerRegister.add(customer);
    }

    public static String generateCustomerID(){
        String customerID = "customer";
        return customerID;
    }
}
