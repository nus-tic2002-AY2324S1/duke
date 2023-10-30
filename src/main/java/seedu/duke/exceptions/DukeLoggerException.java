package seedu.duke.exceptions;

/**
 * DukeLoggerException class is a custom exception class that extends DukeException.
 * It is thrown when there is an error in the DukeLogger class.
 */
public class DukeLoggerException extends DukeException {
    public DukeLoggerException(Exception err) {
        super(err);
    }
}
