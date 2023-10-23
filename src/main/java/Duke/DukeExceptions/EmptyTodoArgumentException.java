package Duke.DukeExceptions;

public class EmptyTodoArgumentException extends DukeException {
    public EmptyTodoArgumentException() {
        super("You did not provide any details on your to do task. Please provide in the format of <Todo + Task Name>.");
    }
}
