package duke.exception;

/**
 * The FileStorageException class represents exceptions specific to file storage operations.
 */
public class FileStorageException extends Exception {
    /**
     * Constructs a new FileStorageException object with the specified error message.
     *
     * @param message A descriptive message explaining the exception.
     */
    public FileStorageException(String message) {
        super(message);
    }
}
