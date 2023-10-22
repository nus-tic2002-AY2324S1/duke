package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

/**
 * Represents a `MarkAsInCompletedCommand` to mark a task as incomplete.
 */
public class MarkAsInCompletedCommand extends Command {

    // The index of the task to be marked as incomplete.
    private final int taskIndex;

    /**
     * Constructs a `MarkAsInCompletedCommand` with the specified item index.
     *
     * @param taskIndex The index of the task to mark as incomplete.
     */
    public MarkAsInCompletedCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the specified task as incomplete.
     */
    @Override
    public void execute() {
        // Get the task to mark as incomplete from the task list using the index.
        Task task = Duke.taskList.get(taskIndex);
        if (!task.isCompleted()) {
            // If the task is not completed, display a message
            UserInterface.MessageDisplay.notMark(task.getTaskName());
        } else {
            // Mark the task as not completed and display a message to prompt the user.
            task.markAsNotCompleted();
            UserInterface.MessageDisplay.unCompleteMessage(Duke.taskList, taskIndex);
        }
    }
}
