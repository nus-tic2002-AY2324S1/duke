package exceptions;

public abstract class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException (String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
