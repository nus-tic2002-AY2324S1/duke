package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an `OnCommand` to list and display user's tasks as of a specific date.
 */
public class OnCommand extends CheckTaskCommand {

    /**
     * Displays the list of user tasks as of the specified date.
     *
     * @param taskList  The list of user tasks to be checked.
     * @param checkDate The specific date for which tasks should be displayed.
     */
    public static void checkTasks(List<Task> taskList, LocalDate checkDate) {
        // Check if there are no tasks in the list
        if (Task.getTotalTasks() == 0) {
            UserInterface.MessageDisplay.print("There's nothing in your list");
            return;
        }
        // Display the date and the tasks as of that date
        System.out.printf("Here are the tasks in your list as of %s\n", checkDate.toString());
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            // Check if the task should be displayed based on the date
            if (taskList.get(i).checkDate(checkDate)) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        }
        System.out.println(UserInterface.MessageDisplay.LINE_BREAK);
    }

    /**
     * Executes the command by displaying the list of user tasks as of a specified date.
     *
     * @param checkedDate The specific date for which tasks should be displayed.
     */
    @Override
    public void execute(LocalDate checkedDate) {
        // Get the list of user tasks from Duke
        List<Task> taskList = Duke.taskList;
        // Call the checkTasks method to display the tasks as of the specified date
        checkTasks(taskList, checkedDate);
    }
}
