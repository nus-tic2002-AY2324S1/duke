package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.io.WonkyLogger;

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

    public void setDone(boolean toSet) throws DukeLoggerException {
        String isDoneLitr = toSet ? "done" : "not done";
        if (isDone == toSet) {
            WonkyLogger.markTypo(description, isDoneLitr);
        } else {
            WonkyLogger.taskMarked(description, isDoneLitr);
            isDone = toSet;
        }
    }
}