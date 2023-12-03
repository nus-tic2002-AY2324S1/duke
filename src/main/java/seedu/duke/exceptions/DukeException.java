package seedu.duke.exceptions;

/**
 * Handles exceptions that are specific to the Duke application.
 */
public abstract class DukeException extends Exception {
    public DukeException(Exception err) {
        super(err);
    }
}
