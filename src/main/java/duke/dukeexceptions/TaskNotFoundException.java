package duke.dukeexceptions;

/**
 * The TaskNotFoundException class is used to indicate that
 * the Duke application cannot find a specified task.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * Constructs a new TaskNotFoundException to informs the user
     * that the Duke application couldn't find the specified task.
     */
    public TaskNotFoundException() {

        super("OOPS!!! I'm sorry, but I can't find this task!");
    }

}
