package nus.duke.data.tasks;

import nus.duke.data.TaskAfterOption;

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
        this(description, isDone, null);
    }

    /**
     * Instantiates a new `Todo` task with the provided description, completion status,
     * and an optional afterOption.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     * @param afterOption An optional afterOption associated with the to-do task.
     *                    It represents additional data relevant to the task execution.
     *                    Use null if no afterOption is provided.
     */
    public Todo(String description, boolean isDone, TaskAfterOption afterOption) {
        super(description, isDone, afterOption);
    }

    @Override
    public String getType() {
        return "T";
    }
}
