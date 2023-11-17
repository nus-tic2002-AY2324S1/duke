package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public ListCommand() {}

    /**
     * {@inheritDoc} Prints the TaskList.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays("Your list has " + taskList.listSize() + " item(s):");
        ui.joshuaSays(taskList.toString());
    }
}
