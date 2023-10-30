package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a `ListCommand` to list and display user's tasks.
 */
public class ListCommand extends Command {

  /**
   * Displays the list of user tasks.
   *
   * @param display  The message display interface to show messages to the user.
   * @param taskList The list of user tasks to be displayed.
   */
  public static void printList(MessageDisplay display, List<Task> taskList) {

    if (Task.getTotalTasks() == 0) {
      display.print("There's nothing in your list");
      return;
    }
    display.print("Here are the tasks in your list:");
    for (int i = 0; i < Task.getTotalTasks(); i++) {
      display.print((i + 1) + "." + taskList.get(i).toString());
    }
    display.print(MessageDisplay.LINE_BREAK);
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
