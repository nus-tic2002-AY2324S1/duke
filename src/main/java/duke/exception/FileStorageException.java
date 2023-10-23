package duke.exception;

public class FileStorageException extends Exception{
    /**
     * Constructor for the FileStorageException class.
     * @param message A descriptive message explaining the exception.
     */
    public FileStorageException(String message){
        super(message);
    }
}
