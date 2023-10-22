package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

/**
 * The `ByeCommand` class represents a command for exiting the Duke task manager application.
 * When executed, this command displays a farewell message and signals the application to exit.
 */
public class ByeCommand extends AbstractCommand {
    /**
     * Instantiates a new `ByeCommand` with the provided arguments.
     *
     * @param args The arguments associated with the bye command (should be empty).
     */
    public ByeCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The bye command should not take any arguments.");
        }

        ui.showMessages("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
