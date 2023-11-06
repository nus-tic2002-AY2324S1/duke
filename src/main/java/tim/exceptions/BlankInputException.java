package tim.exceptions;

public class BlankInputException extends Exception{
    public BlankInputException(String statement){
        super (statement);
    }

}
