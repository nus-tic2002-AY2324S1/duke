package nus.duke.data.tasks;

/**
 * The `Todo` class represents a simple to-do task in Duke.
 * It extends the `AbstractTask` class and includes a description of the task.
 */
public class Todo extends AbstractTask {
    /**
     * Instantiates a new `Todo` task with the provided description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Instantiates a new `Todo` task with the provided description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s", getType(), encodeIsDone(), description);
    }
}
