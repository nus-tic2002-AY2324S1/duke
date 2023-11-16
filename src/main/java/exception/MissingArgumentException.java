package exception;
public class MissingArgumentException extends Exception{
    String argument;

    /**
     *
     * @param argument error message to be thrown
     */
    public MissingArgumentException(String argument){
        super(argument);
        this.argument = argument;
    }

    /**
     *
     * @return error message
     */
    @Override
    public String toString() {
        return argument;
    }
}

