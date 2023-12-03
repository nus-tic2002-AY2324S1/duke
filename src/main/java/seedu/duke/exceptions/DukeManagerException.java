package seedu.duke.exceptions;

/**
 * Represents an exception that occurs within the DukeManager class.
 */
public class DukeManagerException extends DukeException {
    /**
     * Constructs a DukeManagerException with the specified error message.
     *
     * @param err the error message.
     */
    public DukeManagerException(String err) {
        super(new Exception(err));
    }

    /**
     * Constructs a DukeManagerException with the specified cause.
     *
     * @param err the cause of the exception.
     */
    public DukeManagerException(Exception err) {
        super(err);
    }
}
