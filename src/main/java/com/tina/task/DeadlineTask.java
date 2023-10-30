package com.tina.task;

import java.time.LocalDate;

/**
 * The type Deadline task.
 */
public class DeadlineTask extends Task {
    LocalDate by;

    /**
     * Instantiates a new Deadline task.
     *
     * @param taskName the task name
     * @param by       the deadline date
     */
    public DeadlineTask(String taskName, LocalDate by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
    }

    /**
     * Instantiates a new Deadline task.
     *
     * @param taskName the task name
     * @param isDone   the is done status
     * @param by       the deadline date
     */
    public DeadlineTask(String taskName, boolean isDone, LocalDate by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
        this.setDone(isDone);
    }

    /**
     * Gets deadline date.
     *
     * @return the deadline date
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Override toString() to print with task format:
     * [D][ ] [task name] (by: [date])
     * [D][X] [task name] (by: [date])
     *
     * @return the string
     */
    public String toString() {
        return "[D]" + "[" + this.isDone() + "] " + this.getTaskName() + " (by: " + this.by + ")";
    }

    /**
     * Return a string in storage format:
     * D| |[task name]|[date]
     * D|X|[task name]|[date]
     *
     * @return the string
     */
    @Override
    public String toStorage() {
        return "D" + "|" + isDone() + "|" + this.getTaskName() + "|" + this.by;
    }
}
