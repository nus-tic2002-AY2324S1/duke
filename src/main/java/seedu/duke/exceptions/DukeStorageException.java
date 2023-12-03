package seedu.duke.exceptions;

/**
 * Represents an exception that occurs when there is an error with Duke's storage system.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(Exception err) {
        super(err);
    }
}
