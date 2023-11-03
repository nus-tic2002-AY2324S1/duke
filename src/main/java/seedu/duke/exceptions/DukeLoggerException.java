package seedu.duke.exceptions;

/**
 * Occurs when there is an error in the DukeLogger class.
 */
public class DukeLoggerException extends DukeException {
    public DukeLoggerException(Exception err) {
        super(err);
    }
}
