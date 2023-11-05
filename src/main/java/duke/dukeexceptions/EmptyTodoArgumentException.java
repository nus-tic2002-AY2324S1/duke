package duke.dukeexceptions;

/**
 * The EmptyTodoArgumentException class is used to indicate that the user
 * did not provide any details for a to-do task.
 */
public class EmptyTodoArgumentException extends DukeException {

    /**
     * Constructs a new EmptyTodoArgumentException to informs the user
     * that no details were provided for the to-do task and provides
     * an example format for how the information should be provided.
     */
    public EmptyTodoArgumentException() {

        super("You did not provide any details on your to-do task."
            + " Please provide in the format of <Todo + Task Name>.");
    }

}
