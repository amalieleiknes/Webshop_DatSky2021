package portfolio2.packages.Exceptions;

import java.io.IOException;

public class InvalidProductException extends IOException {

    public InvalidProductException(String msg){
        super(msg);     // calling on the constructor to the class it inherits from
    }

}
