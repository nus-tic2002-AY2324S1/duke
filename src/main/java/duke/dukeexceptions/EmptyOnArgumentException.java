package duke.dukeexceptions;

/**
 * The EmptyOnArgumentException class is used to indicate that
 * the user did not provide a specific date when checking for tasks on a particular date.
 */
public class EmptyOnArgumentException extends DukeException {

    /**
     * Constructs a new EmptyOnArgumentException with a predefined error message.
     * This message informs the user that they need to provide a specific date in a particular format
     * to check if a task exists on that date.
     */
    public EmptyOnArgumentException() {

        super("Please provide the specific date that you want to check"
            + " if a task exists in the format of <on task date>");
    }

}
