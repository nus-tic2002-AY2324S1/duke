package com.tina.task;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(TaskEnum.EVENT, taskName);
        this.from = from;
        this.to = to;
    }

    public EventTask(String taskName, boolean isDone, LocalDateTime from, LocalDateTime to) {
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
