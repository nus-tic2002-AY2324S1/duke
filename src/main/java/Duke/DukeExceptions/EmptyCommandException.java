package Duke.DukeExceptions;

public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("You did not provide any command. Please try again.");
    }
}
