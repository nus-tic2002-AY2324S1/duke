package seedu.duke.exceptions;

/**
 * DukeException is an abstract class that extends the Exception class.
 * It is used to handle exceptions that are specific to the Duke application.
 */
public abstract class DukeException extends Exception {
    public DukeException(Exception err) {
        super(err);
    }
}
