package portfolio2.packages.Validator;

// class where we check if the regex is OK before checking if the database can take it in
public class CustomerValidator {


    public static boolean validateEmail(String email) {
        if (!email.isEmpty() && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        if (password.matches("[a-zA-Z0-9._%+!-<>§@£=?$¤€#*&|]{3,}") && !password.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean validateTelephone(String telephone) {
        if (telephone.matches("[0-9+ ]{3,}") && !telephone.isEmpty()){
            return true;
        }
        return false;
    }


}