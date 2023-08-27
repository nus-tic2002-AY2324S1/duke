package seedu.duke.exceptions;

public abstract class DukeException extends Exception {
    public DukeException(String err) {
        super(err);
    }
}
