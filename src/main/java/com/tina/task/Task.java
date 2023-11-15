package com.tina.task;

/**
 * Represents a parent Task.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Instantiates a new Task.
     *
     * @param taskName the task name.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns status of whether the task is done.
     *
     * @return the status.
     */
    public String isDone() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets task status done.
     *
     * @param isDone the is done flag.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets task name.
     *
     * @return the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    @Override
    public abstract String toString();

    public abstract String toStorage();
}