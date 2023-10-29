package duke.command;

import duke.task.Task;

import java.util.List;
import duke.userinterface.UserInterface.MessageDisplay;
/**
 * Represents a `ListCommand` to list and display user's tasks.
 */
public class ListCommand extends Command {

  /**
   * Displays the list of user tasks.
   *
   * @param taskList The list of user tasks.
   */
  public static void printList(MessageDisplay display,List<Task> taskList) {

    if (Task.getTotalTasks() == 0) {
      display.print("There's nothing in your list");
      return;
    }
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < Task.getTotalTasks(); i++) {
      System.out.println((i + 1) + "." + taskList.get(i).toString());
    }
    System.out.println(duke.userinterface.UserInterface.MessageDisplay.LINE_BREAK);
  }

  /**
   * Executes the command by displaying the list of user tasks.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {
    // Call the printList method to display the tasks
    printList(display,taskList);
  }

}
