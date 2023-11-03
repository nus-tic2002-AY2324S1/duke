package seedu.duke.exceptions;

/**
 * Occurs when there is an error in reading user input.
 */
public class DukeScannerException extends DukeException {
    public DukeScannerException(Exception err) {
        super(err);
    }
}
