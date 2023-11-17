package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Represents a command to list all the  tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list along with their statuses.
     *
     * @param taskList The task list to retrieve tasks from.
     * @param ui       The user interface for input and output.
     * @param storage  The storage to read and write data (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        if (!taskList.getTaskList().isEmpty()) {
            int idx = 1;
            UI.showMessage("Here are the tasks in your list: ");
            for (Task task : taskList.getTaskList()) {
                UI.showMessage(idx + ". " + task.getStatusIcon() + " " + task.getDescription());
                idx++;
            }
        } else {
            UI.showMessage("The task list is currently empty. You can begin by adding new tasks.");
        }
    }

    /**
     * Indicates if this command should exit the application.
     *
     * @return false, as this command does not trigger an application exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }
}