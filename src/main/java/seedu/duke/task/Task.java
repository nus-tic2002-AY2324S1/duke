package seedu.duke.task;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeLoggerException;
import seedu.duke.io.WonkyLogger;

/**
 * Represents a task with a description and a completion status.
 * Abstract class that is extended by various types of tasks.
 */
public abstract class Task {

    /**
     * The message format for the status of the task.
     * Contains the index, letter, completion status and description of the task.
     */
    private final String STATUS_MSG = "%s. [%s][%s] %s";

    /**
     * The command associated with the task.
     */
    protected Command command;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * The letter associated with the task.
     */
    protected String letter;

    /**
     * Constructs a new task with the given command, letter and description.
     *
     * @param command The command associated with the task.
     * @param letter The letter associated with the task.
     * @param description The description of the task.
     */
    public Task(Command command, String letter, String description) {
        this.command = command;
        this.letter = letter;
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the status message of the task with the given index.
     *
     * @param idx The index of the task.
     * @return The status message of the task.
     */
    public String getStatusMsg(int idx) {
        return String.format(STATUS_MSG, String.valueOf(idx), letter, isDone ? "X" : " ", description);
    }

    /**
     * Returns the command associated with the task.
     *
     * @return The command associated with the task.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param toSet The completion status to set.
     * @throws DukeLoggerException If there is an error with the logger.
     */
    public void setDone(boolean toSet) throws DukeLoggerException {
        String isDoneLitr = toSet ? "done" : "not done";
        if (isDone == toSet) {
            WonkyLogger.markTypo(description, isDoneLitr);
        } else {
            WonkyLogger.taskMarked(description, isDoneLitr);
            isDone = toSet;
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the isDone of the task.
     *
     * @return the isDone of the task
     */
    public boolean getDone() {
        return isDone;
    }
}