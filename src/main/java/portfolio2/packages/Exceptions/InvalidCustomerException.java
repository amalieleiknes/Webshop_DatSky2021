package portfolio2.packages.Exceptions;

import java.io.IOException;

public class InvalidCustomerException extends IOException {

    public InvalidCustomerException(String msg){
        super(msg);     // calling on the constructpr to the class it inherits from
    }

}
