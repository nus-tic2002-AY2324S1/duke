package task;

/**
 * This class represents a todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * This method returns the string representation of the todo task.
     * @return the string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
