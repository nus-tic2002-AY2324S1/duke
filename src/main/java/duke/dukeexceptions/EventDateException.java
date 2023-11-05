package duke.dukeexceptions;

/**
 * EventDateException is a custom exception class that is thrown when the start date of an event
 * task is after the end date.
 */
public class EventDateException extends DukeException {

    /**
     * Constructs a new EventDateException with a default error message.
     */
    public EventDateException() {
        super("Start date should be before end date!!!");
    }
}
