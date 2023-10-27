package duke.command;

import duke.dukeexceptions.DukeException;

import java.time.LocalDate;

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
  public abstract void execute(LocalDate checkedDate) throws DukeException;

}
