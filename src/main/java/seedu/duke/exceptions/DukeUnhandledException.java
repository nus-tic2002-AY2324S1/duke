package seedu.duke.exceptions;

/**
 * DukeUnhandledException is a custom exception class that extends DukeException.
 * It is thrown when an unhandled exception occurs in the Duke program.
 */
public class DukeUnhandledException extends DukeException {
    public DukeUnhandledException(Exception err) {
        super(err);
    }
}
