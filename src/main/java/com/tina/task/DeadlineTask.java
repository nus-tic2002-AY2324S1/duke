package com.tina.task;

public class DeadlineTask extends Task {
    String by;
    public DeadlineTask(String name, String by) {
        super(name);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
