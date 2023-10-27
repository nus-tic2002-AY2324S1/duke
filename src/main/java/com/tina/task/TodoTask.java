package com.tina.task;

public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super(TaskEnum.TODO, taskName);
    }

    public TodoTask(String taskName, boolean isDone) {
        super(TaskEnum.TODO, taskName);
        this.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.isDone() + "] " + this.getTaskName();
    }

    @Override
    public String toStorage() {
        return "T" + "|" + this.isDone() + "|" + this.getTaskName();
    }
}
