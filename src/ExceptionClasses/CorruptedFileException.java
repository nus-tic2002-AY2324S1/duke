package ExceptionClasses;

public class CorruptedFileException extends DupeException {
    public CorruptedFileException(String message) {
        super(message);
    }
}
