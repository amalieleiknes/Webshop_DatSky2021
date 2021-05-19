import org.junit.Test;
import portfolio2.packages.Validator.AdminValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnittestAdminLogon {


    @Test
    public void testValidLoginAdmin() {
        assertTrue(AdminValidator.validateAdmin("admin", "admin"));
    }

    @Test
    public void testInvalidLoginAdmin() {
        assertFalse(AdminValidator.validateAdmin("", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", ""));
        assertFalse(AdminValidator.validateAdmin(" ", " "));
        assertFalse(AdminValidator.validateAdmin("root", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", "root"));
        assertFalse(AdminValidator.validateAdmin(" admin", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", "admin "));
        assertFalse(AdminValidator.validateAdmin("admin", "SELECT password FROM Password"));
    }


}
