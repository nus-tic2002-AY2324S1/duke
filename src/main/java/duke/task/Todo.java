package duke.task;

/**
 * The Todo class represents a task that need to be done.
 * It extends the Task class and adds specific behavior for tasks
 */
public class Todo extends Task {
    /**
     * Constructor
     */
    public Todo() {
        isDone = false;
    }

    /**
     * Constructor of Todo task take in description sets its abbreviation
     *
     * @param description The description for the Todo task.
     */
    public Todo(String description) {
        super(description);
        setAbbreviation();
    }

    /**
     * Constructor of Todo task take in description and status of the task sets its abbreviation.
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
     *
     */
    public void execute() {
        displayTaskAddedResponse();
    }

    /**
     * set abbreviation for Todo object.
     */
    public void setAbbreviation() {
        abbreviation = 'T';
    }

    /**
     * Overrides the toString method to provide task-specific details.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s", abbreviation, getStatusIcon(), dukeDescription);
    }

}
