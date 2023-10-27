package com.tina.task;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    LocalDate by;
    public DeadlineTask(String taskName, LocalDate by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
    }

    public DeadlineTask(String taskName, boolean isDone, LocalDate by) {
        super(TaskEnum.DEADLINE, taskName);
        this.by = by;
        this.setDone(isDone);
    }

    public LocalDate getBy() {
        return by;
    }

    public String toString() {
        return "[D]" + "[" + this.isDone() + "] " + this.getTaskName() + " (by: " + this.by + ")";
    }

    @Override
    public String toStorage() {
        return "D" + "|" + isDone() + "|" + this.getTaskName() + "|" + this.by;
    }
}
