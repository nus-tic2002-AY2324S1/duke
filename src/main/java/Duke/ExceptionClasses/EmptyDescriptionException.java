package Duke.ExceptionClasses;

public class EmptyDescriptionException extends DupeException {
    public EmptyDescriptionException() {
        super("OOPS!!! The description cannot be be empty.");
    }
}
