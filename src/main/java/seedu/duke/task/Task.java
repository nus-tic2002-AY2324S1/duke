package seedu.duke.task;

import seedu.duke.commands.Command;

public abstract class Task {

    protected Command command;
    protected String description;
    protected boolean isDone;

    public Task(Command command, String description) {
        this.command = command;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusMsg() {
        return (isDone ? "[X]" : "[ ]") + description; // mark done task with X
    }

    public Command getCommand() {
        return command;
    }
}