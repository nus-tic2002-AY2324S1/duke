package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays("Your list has " + taskList.listSize() + " item(s):");
        ui.joshuaSays(taskList.toString());
    }
}
