package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.io.WonkyLogger;

public abstract class Task {

    private final String STATUS_MSG = "%s. [%s][%s] %s";

    protected Command command;
    protected String description;
    protected boolean isDone;
    protected String letter;

    public Task(Command command, String letter, String description) {
        this.command = command;
        this.letter = letter;
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusMsg(int idx) {
        return String.format(STATUS_MSG, String.valueOf(idx), letter, isDone ? "X" : " ", description);
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