package com.tina.task;

import java.time.LocalDate;

public class EventTask extends Task {
    private LocalDate from;
    private LocalDate to;

    public EventTask(String taskName, LocalDate from, LocalDate to) {
        super(TaskEnum.EVENT, taskName);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public EventTask(String taskName, boolean isDone, LocalDate from, LocalDate to) {
        super(TaskEnum.EVENT, taskName);
        this.from = from;
        this.to = to;
        this.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.isDone() + "] " + this.getTaskName() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toStorage() {
        return "E" + "|" + this.isDone() + "|" + this.getTaskName() + "|" + this.from + "|" + this.to;
    }
}
