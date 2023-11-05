package duke.dukeexceptions;

/**
 * The InvalidRescheduleArgumentException class is used to handle invalid rescheduling arguments in
 * Duke. This exception is thrown when the provided reschedule format is incorrect.
 */
public class InvalidRescheduleArgumentException extends DukeException {

    /**
     * Constructs an InvalidRescheduleArgumentException with a default error message.
     * The error message informs the user about the required format for rescheduling tasks.
     */
    public InvalidRescheduleArgumentException() {

        super("Invalid format for rescheduling. Please provide in the format of "
                + "<reschedule + Task index + yyyy-MM-dd HH:mm>");
    }

}
