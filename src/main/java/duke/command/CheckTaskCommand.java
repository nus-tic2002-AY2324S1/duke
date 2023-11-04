package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a command for checking tasks on a specific date.
 */
abstract class CheckTaskCommand {

  /**
   * Executes the command to check tasks without specifying a date.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of tasks to check.
   */
  public abstract void execute(MessageDisplay display, List<Task> taskList);

}
