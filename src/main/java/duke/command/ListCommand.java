package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a `ListCommand` to list and display user's tasks.
 */
public class ListCommand extends CheckTaskCommand {

  /**
   * Displays the list of user tasks.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of user tasks to be displayed.
   */
  public void printList(MessageDisplay display, List<Task> taskList) {
    if (taskList.isEmpty()) {
      display.print("There's nothing in your list");
      return;
    }
    display.print("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      display.print((i + 1) + "." + taskList.get(i).toString());
    }
    display.print(MessageDisplay.LINE_BREAK);
  }

  /**
   * Executes the command to check tasks on the specified date.
   *
   * @param display     The message display interface to show messages to the user.
   * @param taskList    The list of tasks to check for the specified date.
   * @param checkedDate The date for which tasks should be checked.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList, LocalDate checkedDate) {
    // This method is not used in the ListCommand, but it's required by the abstract class.
  }

  /**
   * Executes the command by displaying the list of user tasks.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of user tasks to be displayed.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
    // Call the printList method to display the tasks
    printList(display, taskList);
  }
}
