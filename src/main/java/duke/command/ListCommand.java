package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

import java.util.List;

/**
 * Represents a `ListCommand` to list and display user's tasks.
 */
public class ListCommand extends CheckTaskCommand {

  /**
   * Displays the list of user tasks.
   *
   * @param taskList The list of user tasks to be displayed.
   */
  public void printList(List<Task> taskList) {

    if (taskList.isEmpty()) {
      System.out.println("There's nothing in your list");
      MessageDisplay.printLineBreak();
      return;
    }
    System.out.println("Here are the tasks in your list:");
    MessageDisplay.printLineBreak();
    int index = 1;
    for(Task task:taskList){
      System.out.println(index+ "."+task.toString());
      index++;
    }
    MessageDisplay.printLineBreak();
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
    printList(taskList);
  }

}
