import org.junit.AfterClass;
import org.junit.BeforeClass;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import portfolio2.packages.API.CustomerController;
import portfolio2.packages.DAL.CustomerRepository;
import portfolio2.packages.Objects.Customer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnittestCustomerController {

    //testdata
    private static Customer customer1;


    @InjectMocks
    private CustomerController customercontroller;

    @Mock
    private CustomerRepository customerrepo;

    @Mock
    private DataSource dataSource;

    @BeforeClass
    public static void initialize(){
        customer1 = new Customer("navn", "navnesen", "adresse", "1001", "94849484", "test@test.no", "password");
        List<Customer> customers = new ArrayList<>();

        customers.add(customer1);
    }


    @Test
    public void test_registerCustomer_OK() {
        //arrange
        when(customerrepo.addCustomer(any(Customer.class))).thenReturn("OK");

        //act
        String res = customercontroller.addCustomer(customer1);

        //assert
        assertEquals("OK", res);
    }


}
