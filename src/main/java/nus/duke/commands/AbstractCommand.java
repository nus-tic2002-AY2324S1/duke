package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.storage.Storage;

/**
 * The `AbstractCommand` class serves as the base class for various command objects
 * in the Duke task manager application.
 */
public abstract class AbstractCommand {
    /**
     * The arguments associated with the command.
     */
    protected final String args;

    /**
     * Instantiates a new abstract command with the provided arguments.
     *
     * @param args The arguments associated with the command.
     */
    protected AbstractCommand(String args) {
        assert args != null;

        this.args = args;
    }

    /**
     * Executes the command, operating on the provided task list and storage.
     *
     * @param tasks   The task list containing tasks to be operated upon.
     * @param storage The storage component for reading and writing task data to a file.
     * @return An array of Strings representing the output messages or results of the command execution.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    public abstract String[] execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Checks whether the program should exit.
     *
     * @return {@code true} if the program should exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
