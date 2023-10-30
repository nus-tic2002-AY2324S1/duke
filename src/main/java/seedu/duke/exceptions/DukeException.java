package seedu.duke.exceptions;

public abstract class DukeException extends Exception {
    public DukeException(Exception err) {
        super(err);
    }
}
