package duke.command;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a `MarkAsCompletedCommand` to mark a task as completed.
 */
public class MarkAsCompletedCommand extends ModifyTaskCommand {

  /**
   * Constructs a `MarkAsCompletedCommand` with the specified item index.
   *
   * @param itemIndex The index of the task to mark as completed.
   */
  public MarkAsCompletedCommand(int itemIndex) {

    super(itemIndex);
  }

  /**
   * Executes the command by marking the specified task as completed.
   *
   * @param fileStorage The file storage handler for saving tasks to a file.
   * @param display     The message display interface to show messages to the user.
   * @param taskList    The list of tasks in which the task will be marked as completed.
   */
  @Override
  public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
    // Get the task to mark as completed from the task list
    Task task = taskList.get(itemIndex);
    if (task.isCompleted()) {
      // If the task is already completed, prompt the user
      display.alreadyMark(task.getTaskName());
    } else {
      // Mark the task as completed and display a completion message
      task.markAsCompleted();
      storeDuke(fileStorage, taskList);
      display.completeMessage(taskList, itemIndex);
    }
  }

}
