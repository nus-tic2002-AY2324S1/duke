package duke.exception;

/**
 * Represents exceptions specific to invalid arguments passed to methods.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Constructor for the InvalidArgumentException class.
     *
     * @param message A descriptive message explaining the exception.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
