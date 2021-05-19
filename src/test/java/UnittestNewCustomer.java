import org.junit.Test;
import portfolio2.packages.Validator.AdminValidator;
import portfolio2.packages.Validator.CustomerValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnittestNewCustomer {

    // testing if email is valid
    @Test
    public void testValidEmail() {
        assertTrue(CustomerValidator.validateEmail("admin@admin.no"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(CustomerValidator.validateEmail(""));
    }

    // testing if password is valid



    // testing if telephone is valid
}
