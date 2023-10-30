package seedu.duke.exceptions;

public class DukeUnhandledException extends DukeException {
    public DukeUnhandledException(Exception err) {
        super(err);
    }
}
