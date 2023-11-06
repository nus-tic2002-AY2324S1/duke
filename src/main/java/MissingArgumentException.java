public class MissingArgumentException extends Exception{
    String argument;
    public MissingArgumentException(String argument){
        super(argument);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return argument;
    }
}

