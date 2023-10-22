package nus.duke.exceptions;

/**
 * The `DukeException` class is the base class for exceptions specific to the Duke application.
 * It extends the standard Java `Exception` class and provides custom error handling for Duke.
 */
public abstract class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception with the specified error message.
     *
     * @param errorMessage The error message describing the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new Duke exception with the specified error message and the underlying cause.
     *
     * @param errorMessage The error message describing the exception.
     * @param cause        The cause of the exception, typically another exception that triggered this one.
     */
    public DukeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
