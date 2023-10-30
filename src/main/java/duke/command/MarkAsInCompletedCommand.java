package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

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
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of tasks in which the task will be marked as incomplete.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
    // Get the task to mark as incomplete from the task list using the index.
    Task task = taskList.get(taskIndex);
    if (!task.isCompleted()) {
      // If the task is not completed, display a message
      display.notMark(task.getTaskName());
    } else {
      // Mark the task as not completed and display a message to prompt the user.
      task.markAsNotCompleted();
      display.unCompleteMessage(taskList, taskIndex);
    }
  }

}
