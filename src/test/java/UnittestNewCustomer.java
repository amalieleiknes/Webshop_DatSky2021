import org.junit.Test;
import portfolio2.packages.Validator.CustomerValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnittestNewCustomer {

    // testing if email is valid
    @Test
    public void testValidEmail() {
        assertTrue(CustomerValidator.validateEmail("admin@admin.no"));
        assertTrue(CustomerValidator.validateEmail("admin.admin_admin-admin@admin.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(CustomerValidator.validateEmail(""));
        assertFalse(CustomerValidator.validateEmail("admin.no"));
        assertFalse(CustomerValidator.validateEmail("admin/admin.no"));
        assertFalse(CustomerValidator.validateEmail("@admin.no"));
        assertFalse(CustomerValidator.validateEmail("admin@admin"));
    }

    // testing if password is valid
    @Test
    public void testValidPassword() {
        assertTrue(CustomerValidator.validatePassword("password"));
        assertTrue(CustomerValidator.validatePassword("pword123!"));
        assertTrue(CustomerValidator.validatePassword("5gT8¤3a?p!?mmmooo"));

    }

    @Test
    public void testInvalidPassword() {
        assertFalse(CustomerValidator.validatePassword(""));
        assertFalse(CustomerValidator.validatePassword("             "));
        assertFalse(CustomerValidator.validatePassword(" "));
        assertFalse(CustomerValidator.validatePassword("kk"));
        assertFalse(CustomerValidator.validatePassword("æøåæøå"));
    }


    // testing if telephone is valid
    @Test
    public void testValidTelephone() {
        assertTrue(CustomerValidator.validateTelephone("+47 0123456789"));
        assertTrue(CustomerValidator.validateTelephone("113"));
        assertTrue(CustomerValidator.validateTelephone("10100101"));
        assertTrue(CustomerValidator.validateTelephone("0078 01234567892135410"));
    }

    @Test
    public void testInvalidTelephone() {
        assertFalse(CustomerValidator.validateTelephone("scam"));
        assertFalse(CustomerValidator.validateTelephone("0"));
        assertFalse(CustomerValidator.validateTelephone("911?"));
        assertFalse(CustomerValidator.validateTelephone("<98989898>"));
    }


}
