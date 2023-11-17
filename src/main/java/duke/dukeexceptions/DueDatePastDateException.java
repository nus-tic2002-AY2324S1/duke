package duke.dukeexceptions;

/**
 * DueDatePastDateException is a custom exception class that is thrown when the end date
 * of a task is before the current date or time.
 */
public class DueDatePastDateException extends DukeException {

    /**
     * Constructs a new DueDatePastDateException with a default error message.
     */
    public DueDatePastDateException() {

        super("You can't reschedule the due date to the past!");
    }

}
