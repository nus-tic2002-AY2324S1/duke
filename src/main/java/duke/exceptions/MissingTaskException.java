package duke.exceptions;

public class MissingTaskException extends Exception {
    String message;

    public MissingTaskException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
