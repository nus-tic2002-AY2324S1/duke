package com.tina.task;

import com.tina.service.Parser;
import java.time.LocalDateTime;

/**
 * Represents a Deadline task.
 */
public class DeadlineTask extends Task {
    LocalDateTime by;

    /**
     * Instantiates a new Deadline task.
     *
     * @param taskName the task name.
     * @param by       the deadline date.
     */
    public DeadlineTask(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Instantiates a new Deadline task.
     *
     * @param taskName the task name.
     * @param isDone   the is done status.
     * @param by       the deadline date.
     */
    public DeadlineTask(String taskName, boolean isDone, LocalDateTime by) {
        super(taskName);
        this.by = by;
        this.setDone(isDone);
    }

    /**
     * Gets deadline date.
     *
     * @return the deadline date.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Overrides toString() to print with task format:
     * [D][ ] [task name] (by: [date])
     * [D][X] [task name] (by: [date])
     *
     * @return the string.
     */
    public String toString() {
        return "[D]" + "[" + this.isDone() + "] " + this.getTaskName() + " (by: " + Parser.parseLocalDateTime(this.by) + ")";
    }

    /**
     * Returns a string in storage format:
     * D| |[task name]|[date]
     * D|X|[task name]|[date]
     *
     * @return the string.
     */
    @Override
    public String toStorage() {
        return "D" + "|" + isDone() + "|" + this.getTaskName() + "|" + this.by;
    }
}
