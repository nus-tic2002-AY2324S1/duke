package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import duke.filehandler.FileStorage;
import java.util.List;

/**
 * Represents a `MarkAsInCompletedCommand` to mark a task as incomplete.
 */
public class MarkAsInCompletedCommand extends ModifyTaskCommand {

  /**
   * Constructs a `MarkAsInCompletedCommand` with the specified item index.
   *
   * @param itemIndex The index of the task to mark as incomplete.
   */
  public MarkAsInCompletedCommand(int itemIndex) {
    super(itemIndex);
  }

  /**
   * Executes the command by marking the specified task as incomplete.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of tasks in which the task will be marked as incomplete.
   */
  @Override
  public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
    // Get the task to mark as incomplete from the task list using the index.
    Task task = taskList.get(itemIndex);
    if (!task.isCompleted()) {
      // If the task is not completed, display a message
      display.notMark(task.getTaskName());
    } else {
      // Mark the task as not completed and display a message to prompt the user.
      task.markAsNotCompleted();
      storeDuke(fileStorage, taskList);
      display.unCompleteMessage(taskList, itemIndex);
    }
  }

}
