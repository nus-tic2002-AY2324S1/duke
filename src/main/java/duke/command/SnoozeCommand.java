package duke.command;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a `SnoozeCommand` to reschedule a task by extending its end date.
 */
public class SnoozeCommand extends Command {

  // The index of the task to be rescheduled.
  private final int itemIndex;

  /**
   * Constructs a `SnoozeCommand` with the specified item index to reschedule a task.
   *
   * @param itemIndex The index of the task to be rescheduled.
   */
  public SnoozeCommand(int itemIndex) {

    this.itemIndex = itemIndex;
  }

  /**
   * Executes the command by extending the end date of the specified task in the task list.
   *
   * @param fileStorage The file storage handler for saving tasks to a file.
   * @param display     The message display interface to show messages to the user.
   * @param taskList    The list of tasks containing the task to be rescheduled.
   */
  @Override
  public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {

    taskList.get(itemIndex).changeEndDate(taskList.get(itemIndex).getTaskEndDate().plusHours(12));
    storeDuke(fileStorage, taskList);
    display.snoozeMessage(taskList, taskList.get(itemIndex));
  }

}
