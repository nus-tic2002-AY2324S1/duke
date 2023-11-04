package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an `OnCommand` to list and display user's tasks as of a specific date.
 */
public class OnCommand extends CheckTaskCommand {

  /**
   * Displays the list of user tasks as of the specified date.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of user tasks to be checked.
   * @param date     The specific date for which tasks should be displayed.
   */
  public void checkTasks(MessageDisplay display, List<Task> taskList, LocalDate date) {
    // Check if there are no tasks in the list
    if (taskList.isEmpty()) {
      display.print("There's nothing on " + date);
      return;
    }
    boolean headerPrinted = false;
    // Display the date and the tasks as of that date
    for (int i = 0; i < taskList.size(); i++) {
      Task task = taskList.get(i);
      // Check if the task should be displayed based on the date
      if (task.checkDate(date)) {
        if (!headerPrinted) {
          display.print("Here are the tasks in your list as of " + date);
          headerPrinted = true;
        }
        System.out.println((i + 1) + "." + task);
        if (i == taskList.size() - 1) {
          System.out.println(MessageDisplay.LINE_BREAK);
        }
      }
    }
    if (!headerPrinted) {
      display.print(String.format("There's nothing on %s", date.toString()));
    }
  }

  /**
   * Executes the command by displaying the list of user tasks as of a specified date.
   *
   * @param display     The message display interface to show messages to the user.
   * @param taskList    The list of user tasks to be checked.
   * @param checkedDate The specific date for which tasks should be displayed.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList, LocalDate checkedDate) {
    // Call the checkTasks method to display the tasks as of the specified date
    checkTasks(display, taskList, checkedDate);
  }

  /**
   * Executes the command without specifying a date. This method is not used in the OnCommand.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of user tasks.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
    // This method is not used in the OnCommand.
  }

}
