package com.tina.task;

import java.time.LocalDate;

/**
 * Represents a Event task.
 */
public class EventTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Instantiates a new Event task.
     *
     * @param taskName the task name.
     * @param from     the from date.
     * @param to       the to date.
     */
    public EventTask(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets from date.
     *
     * @return the from date.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets to date.
     *
     * @return the to date.
     */
    public LocalDate getTo() {
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
    public EventTask(String taskName, boolean isDone, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.isDone() + "] " + this.getTaskName() + " (from: " + this.from + " to: " + this.to + ")";
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
