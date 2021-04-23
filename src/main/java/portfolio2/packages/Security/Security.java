package portfolio2.packages.Security;

public class Security {

    public static boolean checkAdminLogin(String username, String password){
        if(username.equals("Admin") && password.equals("Admin")){
            return true;
        }
        else{
            return false;
        }
    }
}
