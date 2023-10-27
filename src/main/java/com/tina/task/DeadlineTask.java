package com.tina.task;

public class DeadlineTask extends Task {
    String by;
    public DeadlineTask(String taskName, String by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
    }

    public DeadlineTask(String taskName, boolean isDone, String by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
        this.setDone(isDone);
    }

    public String toString() {
        return "[D]" + "[" + this.isDone() + "] " + this.getTaskName() + " (by: " + this.by + ")";
    }

    @Override
    public String toStorage() {
        return "D" + "|" + isDone() + "|" + this.getTaskName() + "|" + this.by;
    }
}
