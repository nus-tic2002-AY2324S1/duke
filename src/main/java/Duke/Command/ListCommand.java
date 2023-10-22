package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

import java.util.List;

/**
 * Represents a `ListCommand` to list and display user's tasks.
 */
public class ListCommand extends Command {

    /**
     * Displays the list of user tasks.
     *
     * @param taskList The list of user tasks.
     */
    public static void printList(List<Task> taskList) {
        if (Task.getTotalTasks() == 0) {
            UserInterface.MessageDisplay.print("There's nothing in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(UserInterface.MessageDisplay.LINE_BREAK);
    }

    /**
     * Executes the command by displaying the list of user tasks.
     */
    @Override
    public void execute() {
        // Get the list of user tasks from Duke
        List<Task> taskList = Duke.taskList;
        // Call the printList method to display the tasks
        printList(taskList);
    }
}
