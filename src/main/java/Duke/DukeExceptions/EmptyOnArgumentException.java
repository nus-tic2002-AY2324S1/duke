package Duke.DukeExceptions;

public class EmptyOnArgumentException extends DukeException {
    public EmptyOnArgumentException() {
        super("Please provide the specific date that you want to check if a task exists in the format of <on task date>");
    }
}
