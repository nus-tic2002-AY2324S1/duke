package seedu.duke.exceptions;

public class DukeManagerException extends DukeException {
    public DukeManagerException(Exception err) {
        super(err);
    }

    public DukeManagerException(String err) {
        super(new Exception(err));
    }
}
