package com.tina.task;

public abstract class Task {
    private final TaskEnum taskType;
    private final String taskName;
    private boolean isDone = false;

    public Task(TaskEnum taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
    }

    public String isDone() {
        return (isDone ? "X" : " ");
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskEnum getTaskType() {
        return taskType;
    }

    @Override
    public abstract String toString();

    public abstract String toStorage();
}