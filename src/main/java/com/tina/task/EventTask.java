package com.tina.task;

import com.tina.service.Parser;
import java.time.LocalDateTime;

/**
 * Represents a Event task.
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Instantiates a new Event task.
     *
     * @param taskName the task name.
     * @param from     the from date.
     * @param to       the to date.
     */
    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets from date.
     *
     * @return the from date.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets to date.
     *
     * @return the to date.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Instantiates a new Event task.
     *
     * @param taskName the task name.
     * @param isDone   the is done flag.
     * @param from     the from date.
     * @param to       the to date.
     */
    public EventTask(String taskName, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.isDone() + "] " + this.getTaskName() + " " + "(from: " + Parser.parseLocalDateTime(this.from) + " to: " + Parser.parseLocalDateTime(this.to) + ")";
    }

    /**
     * Returns a string in storage file format.
     *
     * @return storage format string.
     */
    @Override
    public String toStorage() {
        return "E" + "|" + this.isDone() + "|" + this.getTaskName() + "|" + this.from + "|" + this.to;
    }
}
