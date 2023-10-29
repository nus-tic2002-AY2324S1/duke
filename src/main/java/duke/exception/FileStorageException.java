package duke.exception;

/**
 * The FileStorageException class represents exceptions specific to file storage operations.
 */
public class FileStorageException extends Exception {
    /**
     * Constructor for the FileStorageException class.
     *
     * @param message A descriptive message explaining the exception.
     */
    public FileStorageException(String message) {
        super(message);
    }
}
