package Duke.DukeExceptions;

public class EmptyEventArgumentException extends DukeException {
    public EmptyEventArgumentException() {
        super("You did not provide any details on your event task. Please provide in the format of <event + Task Name + /from + From time + /to + To time>.");
    }
}
