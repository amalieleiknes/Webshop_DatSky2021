package portfolio2.packages.Validator;

public class AdminValidator {
    public static boolean validateAdmin(String username, String password){
        return username.matches("admin") && password.matches("admin");
    }
}
