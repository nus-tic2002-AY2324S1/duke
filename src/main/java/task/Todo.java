package task;

/**
 * This class represents a todo task.
 */
public class Todo extends Task {

    /**
     * This is the constructor of the todo task.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method returns the string representation of the todo task.
     *
     * @return the string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // implement clone function
    @Override
    public Todo clone() {
        Todo todo = new Todo(this.description);
        todo.isDone = this.isDone;
        return todo;
    }

}
