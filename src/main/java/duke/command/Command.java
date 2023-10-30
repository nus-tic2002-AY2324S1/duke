package duke.command;

import duke.dukeexceptions.DukeException;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import duke.filehandler.FileStorage;
import java.util.List;

/**
 * Represents a base command in the Duke application.
 */
abstract class Command {

  /**
   * Executes the command.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of tasks that the command will operate on.
   * @throws DukeException If an error occurs while executing the command.
   */
  public abstract void execute(FileStorage fileStorage,MessageDisplay display, List<Task> taskList) throws DukeException;

  /**
   * Stores the task list for future loading of tasks.
   *
   * @param taskList The list of tasks to be stored.
   */
  public void storeDuke(FileStorage fileStorage, List<Task> taskList) {
    fileStorage.store(taskList);
  }

}
