package Duke.DukeExceptions;

public class InvalidDateFormatException extends DukeException {

    public InvalidDateFormatException() {
        super("Please provide date in the format of yyyy-mm-dd hh:mm:ss");
    }
}
