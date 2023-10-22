package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

/**
 * Represents a `DeleteCommand` to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    // The index of the task to be deleted.
    private final int itemIndex;

    /**
     * Constructs a `DeleteCommand` with the specified item index to delete.
     *
     * @param itemIndex The index of the task to be deleted.
     */
    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the command by removing the specified task from the task list.
     */
    @Override
    public void execute() {
        // Reduce the size of the task list.
        Task.removeTask();
        // Remove the task from the task list
        Task deletedTask = Duke.taskList.remove(itemIndex);
        // Display a message indicating the deleted task
        UserInterface.MessageDisplay.deleteMessage(deletedTask);
    }
}
