public class StorageOperationException extends DukeException {
    public StorageOperationException(String errorMessage) {
        super(errorMessage);
    }

    public StorageOperationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
