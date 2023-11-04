package duke.exception;

/**
 * Represents exceptions specific to invalid arguments passed to methods.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Constructs a new InvalidArgumentException object with the specified error message.
     *
     * @param message A descriptive message explaining the exception.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
