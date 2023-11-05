package duke.dukeexceptions;

/**
 * EmptyFindArgumentException is thrown when a user attempts to execute a find command without
 * providing a specific keyword.
 */
public class EmptyFindArgumentException extends DukeException {

    /**
     * Constructs an `EmptyFindArgumentException` with a default error message.
     */
    public EmptyFindArgumentException() {

        super("Please provide the specific keyword that you want to check if a task exists.");
    }

}
