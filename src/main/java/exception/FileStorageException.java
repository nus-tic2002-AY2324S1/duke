package exception;
public class FileStorageException extends Exception {
    /**
     * returns error message of file storage exception
     * 
     * @param message error message to be thrown
     */
    public FileStorageException(String message) {
        super(message);
    }
}
