package com.tina.task;

public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String taskName, String from, String to) {
        super(TaskEnum.EVENT, taskName);
        this.from = from;
        this.to = to;
    }

    public EventTask(String taskName, boolean isDone, String from, String to) {
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
