package duke.command;

import duke.Duke;
import duke.dukeexceptions.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * Represents a base command in the Duke application.
 */
abstract class Command {

  /**
   * Executes the command.
   *
   * @throws DukeException If an error occurs while executing the command.
   */
  public abstract void execute() throws DukeException;

  /**
   * Stores the task list for future loading of tasks.
   */
  public void storeDuke() {

    List<Task> taskList = Duke.taskList;
    Duke.fileStorage.fileStorage(taskList);
  }

}

