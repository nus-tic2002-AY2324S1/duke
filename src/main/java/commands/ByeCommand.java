package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

import java.io.IOException;

/**
 * Represents a bye command.
 */
public class ByeCommand extends Command{
    /**
     * {@inheritDoc} Saves tasks from latest session in storage and prints exit message.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            storage.save(taskList);
            ui.printSaveMessage();
        } catch (IOException e) {
            ui.joshuaSays(e.getMessage());
        }
        super.isExit = true;
        ui.printExitMessage();;
    }
}
