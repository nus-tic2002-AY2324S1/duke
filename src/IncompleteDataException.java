public class IncompleteDataException extends DupeException {
    public IncompleteDataException() {
        super("OOPS!!! The task is missing essential data.");
    }
}
