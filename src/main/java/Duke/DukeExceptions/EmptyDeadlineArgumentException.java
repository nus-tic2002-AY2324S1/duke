package Duke.DukeExceptions;

public class EmptyDeadlineArgumentException extends DukeException {
    public EmptyDeadlineArgumentException() {
        super("You did not provide any details on your deadline task. Please provide in the format of <deadline + Task Name + /by + by time>.");
    }
}
