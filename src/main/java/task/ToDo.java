package task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }
    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
