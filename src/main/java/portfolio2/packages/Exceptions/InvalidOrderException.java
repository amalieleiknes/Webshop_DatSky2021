package portfolio2.packages.Exceptions;

import java.io.IOException;

public class InvalidOrderException extends IOException {

    public InvalidOrderException(String msg){
        super(msg);     // calling on the constructor to the class it inherits from
    }

}
