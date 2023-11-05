package duke.dukeexceptions;

/**
 * The EmptyCommandException class is to indicate that the user
 * did not provide any command when interacting with the Duke application.
 */
public class EmptyCommandException extends DukeException {

    /**
     * Constructs a message that informs the user
     * no command was provided and asks them to try again.
     */
    public EmptyCommandException() {

        super("You did not provide any command. Please try again.");
    }

}
