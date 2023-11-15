package exceptions;

public class EmptyListException extends Exception {
    public String message;

    public EmptyListException(String string) {
        message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
