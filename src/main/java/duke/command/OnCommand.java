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
   * @param display   The message display interface to show messages to the user.
   * @param taskList  The list of user tasks to be checked.
   * @param checkDate The specific date for which tasks should be displayed.
   */
  public static void checkTasks(MessageDisplay display, List<Task> taskList, LocalDate checkDate) {
    // Check if there are no tasks in the list
    if (Task.getTotalTasks() == 0) {
      display.print("There's nothing in your list");
      return;
    }
    // Display the date and the tasks as of that date
    display.print(String.format("Here are the tasks in your list as of %s", checkDate.toString()));
    for (int i = 0; i < Task.getTotalTasks(); i++) {
      // Check if the task should be displayed based on the date
      if (taskList.get(i).checkDate(checkDate)) {
        System.out.println((i + 1) + "." + taskList.get(i).toString());
      }
    }
    System.out.println(display.LINE_BREAK);
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
   * @param display
   * @param taskList
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
  }

}
