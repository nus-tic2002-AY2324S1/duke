package duke.task;

/**
 * The Todo class represents a task that need to be done.
 * It extends the Task class and adds specific behavior for tasks
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        setAbbreviation();
    }

    /**
     * Constructs a new Todo task with the given description and marks it as done or undone based on the specified
     * status.
     *
     * @param isDone      The status of the task.
     * @param description The description for the Todo task.
     */
    public Todo(boolean isDone, String description) {
        super(description);
        setAbbreviation();
        markAsDone(isDone);
    }

    /**
     * Executes the task and handles the response.
     */
    public void execute() {
        displayTaskAddedResponse();
    }

    /**
     * sets abbreviation for Todo object.
     */
    public void setAbbreviation() {
        abbreviation = 'T';
    }

    /**
     * @return A formatted string representing the task.
     * @inheritDoc Returns a string representation of the task, including its abbreviation, status icon, and
     * description.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s", abbreviation, getStatusIcon(), dukeDescription);
    }

}
