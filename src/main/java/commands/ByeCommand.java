package commands;

import storage.Storage;
import wargames.JoshuaUi;
import wargames.TaskList;

import java.io.IOException;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        try {
            storage.save(taskList);
            ui.printSaveMessage();
        } catch (IOException e) {
            ui.joshuaSays(e.getMessage());
        }
        ui.printExitMessage();;
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}
