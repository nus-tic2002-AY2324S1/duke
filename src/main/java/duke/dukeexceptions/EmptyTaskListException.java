package duke.dukeexceptions;

/**
 * Custom exception class for handling cases where a task list is empty.
 * Extends the DukeException class and provides a specific error message.
 */
public class EmptyTaskListException extends DukeException {

    /**
     * Constructor for EmptyTaskListException.
     * Sets the error message to be displayed when this exception is thrown.
     */
    public EmptyTaskListException() {
        super("Your task list is empty!");

    }

}
