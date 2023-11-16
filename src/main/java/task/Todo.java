package task;


/**
 * This class represents a todo task.
 * Todo is a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Returns a todo task.
     * This is the constructor of the todo task.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStorageString() {
        String type = "T";
        return type + " || " + super.toStorageString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Task clone() {
        Todo todo = new Todo(this.description);
        todo.isDone = this.isDone;
        return todo;
    }

}
