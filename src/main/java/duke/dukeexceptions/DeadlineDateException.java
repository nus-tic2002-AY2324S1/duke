package duke.dukeexceptions;

/**
 * DeadlineDateException is a custom exception class that is thrown when the end date
 * of a task is before the current date or time.
 */
public class DeadlineDateException extends DukeException {

    /**
     * Constructs a new DeadlineDateException with a default error message.
     */
    public DeadlineDateException() {

        super("You can't reschedule the deadline to a past date!");
    }

}
