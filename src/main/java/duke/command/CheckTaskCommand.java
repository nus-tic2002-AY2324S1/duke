package duke.command;

import duke.dukeexceptions.DukeException;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command for checking tasks on a specific date.
 */
abstract class CheckTaskCommand {

  /**
   * Executes the command.
   *
   * @param checkedDate The date for which tasks should be checked.
   * @throws DukeException If an error occurs while executing the command.
   */
  public abstract void execute(MessageDisplay display, List<Task> taskList, LocalDate checkedDate) throws DukeException;

}
