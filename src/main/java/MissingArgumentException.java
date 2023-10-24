public class MissingArgumentException extends Exception{
    String argument;
    public MissingArgumentException(){}
    public MissingArgumentException(String argument){
        super(argument);
        this.argument = argument;
    }
    public String getArgument(){
        return argument;
    }

    @Override
    public String toString() {
        return argument;
    }
}

