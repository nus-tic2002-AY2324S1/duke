package duke.command;

import duke.Duke;
import duke.task.Task;

/**
 * Represents an `AddTaskCommand` that adds tasks to the Duke application.
 */
abstract class AddTaskCommand extends Command {

  // The name of the task to be added
  protected final String taskName;

  /**
   * Constructs an `AddTaskCommand` with the specified task name.
   *
   * @param taskName The name of the task to be added.
   */
  public AddTaskCommand(String taskName) {

    this.taskName = taskName;
  }

  /**
   * Adds a task to the task list and prompts the user with a successfully added message.
   *
   * @param task The task to be added.
   */
  protected void addTask(Task task) {

    Duke.taskList.add(task);
    int itemIndex = Task.getTotalTasks() - 1;
    storeDuke();
    duke.userinterface.UserInterface.MessageDisplay.addedMessage(Duke.taskList, itemIndex);
  }

}
