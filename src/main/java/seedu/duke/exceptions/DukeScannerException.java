package seedu.duke.exceptions;

/**
 * DukeScannerException class is a custom exception class that extends DukeException.
 * It is thrown when there is an error in scanning user input.
 */
public class DukeScannerException extends DukeException {
    public DukeScannerException(Exception err) {
        super(err);
    }
}
