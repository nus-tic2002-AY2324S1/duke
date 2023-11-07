package commands;

import storage.Storage;
import wargames.JoshuaUi;
import wargames.TaskList;

/**
 * Represents an invalid command
 */
public class InvalidCommand extends Command {
    public final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays(errorMessage);
    }
}
