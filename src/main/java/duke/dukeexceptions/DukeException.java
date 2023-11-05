package duke.dukeexceptions;

/**
 * The DukeException class is a custom exception class for the Duke application.
 * It extends the base Exception class and is used to represent exceptional conditions
 * specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {

        super(message);
    }

}
