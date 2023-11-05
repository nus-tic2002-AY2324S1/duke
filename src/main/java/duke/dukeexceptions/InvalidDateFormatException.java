package duke.dukeexceptions;

/**
 * The InvalidDateFormatException class is used to indicate that the user
 * provided a date in an invalid format.
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructs a new InvalidDateFormatException to informs the user
     * that the date format they provided is invalid and provides an example format.
     */
    public InvalidDateFormatException() {

        super("Please provide date in the format of yyyy-mm-dd hh:mm:ss");
    }

}
