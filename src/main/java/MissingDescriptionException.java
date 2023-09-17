public class MissingDescriptionException extends Exception{
    String message;
    public MissingDescriptionException(){}
    public MissingDescriptionException(String message){
        super(message);

        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
