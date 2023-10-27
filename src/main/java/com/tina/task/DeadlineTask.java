package com.tina.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    LocalDateTime by;
    public DeadlineTask(String taskName, LocalDateTime by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
    }

    public DeadlineTask(String taskName, boolean isDone, LocalDateTime by) {
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
