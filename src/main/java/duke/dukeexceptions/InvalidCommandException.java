package duke.dukeexceptions;

/**
 * The InvalidCommandException class is used to indicate that the user
 * entered an invalid or unrecognized command.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs a new InvalidCommandException to informs the user
     * that the command they entered is not recognized by the Duke application.
     */
    public InvalidCommandException() {

        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
