package com.tina.task;

/**
 * Represents a Todo task.
 */
public class TodoTask extends Task {

    /**
     * Instantiates a new Todo task.
     *
     * @param taskName the task name.
     */
    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Instantiates a new Todo task.
     *
     * @param taskName the task name.
     * @param isDone   the is done flag.
     */
    public TodoTask(String taskName, boolean isDone) {
        super(taskName);
        this.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.isDone() + "] " + this.getTaskName();
    }

    @Override
    public String toStorage() {
        return "T" + "|" + this.isDone() + "|" + this.getTaskName();
    }
}
