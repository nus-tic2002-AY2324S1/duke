package seedu.duke.exceptions;

/**
 * Occurs when an unhandled exception takes place in the Duke program.
 */
public class DukeUnhandledException extends DukeException {
    public DukeUnhandledException(Exception err) {
        super(err);
    }
}
