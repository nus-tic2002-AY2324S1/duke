package ExceptionClasses;

public class IncompleteDataException extends DupeException {
    public IncompleteDataException() {
        super("OOPS!!! The task is missing essential data.");
    }
    public IncompleteDataException(String additionalMessage) {
        super("OOPS!!! The task is missing essential data. " + additionalMessage);
    }
}
