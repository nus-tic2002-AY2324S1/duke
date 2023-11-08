package commands;

import storage.Storage;
import wargames.JoshuaUi;
import wargames.TaskList;

/**
 * Represents an invalid command
 */
public class InvalidCommand extends Command {
    public static final String INVALID_COMMAND_MESSAGE = "Please be more articulate, Professor Falken.\n" +
            "Enter \"help\" to see the list of available commands.";
    public final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays(errorMessage);
    }
}
