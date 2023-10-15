package Duke.DukeExceptions;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("OOPS!!! I'm sorry, but I can't find this task!");
    }
}
