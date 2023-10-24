package duke.command;

import duke.Duke;
import duke.task.Task;

/**
 * Represents a `MarkAsCompletedCommand` to mark a task as completed.
 */
public class MarkAsCompletedCommand extends Command {

  // The index of the task to be marked as completed.
  private final int itemIndex;

  /**
   * Constructs a `MarkAsCompletedCommand` with the specified item index.
   *
   * @param itemIndex The index of the task to mark as completed.
   */
  public MarkAsCompletedCommand(int itemIndex) {

    this.itemIndex = itemIndex;
  }

  /**
   * Executes the command by marking the specified task as completed.
   */
  @Override
  public void execute() {
    // Get the task to mark as completed from the task list
    Task task = Duke.taskList.get(itemIndex);
    if (task.isCompleted()) {
      // If the task is already completed, prompt the user
      duke.userinterface.UserInterface.MessageDisplay.alreadyMark(task.getTaskName());
    } else {
      // Mark the task as completed and display a completion message
      task.markAsCompleted();
      duke.userinterface.UserInterface.MessageDisplay.completeMessage(Duke.taskList, itemIndex);
    }
  }

}
