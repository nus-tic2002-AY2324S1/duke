package duke.command;

import java.time.LocalDate;
import java.util.List;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

/**
 * Represents an `OnCommand` to list and display user's tasks as of a specific date.
 */
public class OnCommand extends CheckTaskCommand {

    private final LocalDate checkedDate;
    /**
     * Constructs a new OnCommand object with a specified date.
     *
     * @param checkedDate The date associated with this command.
     */
    public OnCommand(LocalDate checkedDate) {

        this.checkedDate = checkedDate;
    }

    /**
     * Displays the list of user tasks as of the specified date.
     *
     * @param taskList The list of user tasks to be checked.
     * @param date     The specific date for which tasks should be displayed.
     */
    void checkTasks(List<Task> taskList, LocalDate date) {
        assert !taskList.isEmpty() : "Task list can't be empty!";
        boolean printed = false;
        int index = 1;
        // Display the date and the tasks as of that date
        for (Task task : taskList) {
            // Check if the task should be displayed based on the date
            if (task.checkDate(date)) {
                if (!printed) {
                    System.out.println("Here are the tasks in your list as of " + date);
                    MessageDisplay.printLineBreak();
                    printed = true;
                }
                System.out.println(index + "." + task);
                index++;
            }
        }
        if (printed) {
            MessageDisplay.printLineBreak();
        }

        if (!printed) {
            System.out.printf("There's nothing on %s%n", date.toString());
            MessageDisplay.printLineBreak();
        }
    }

    /**
     * Executes the command by displaying the list of user tasks as of a specified date.
     *
     * @param display  The message display interface to show messages to the user.
     * @param taskList The list of user tasks to be checked.
     */
    @Override
    public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
        // Check if there are no tasks in the list
        if (taskList.isEmpty()) {
            System.out.println("There's nothing on " + checkedDate);
            MessageDisplay.printLineBreak();
            return;
        }
        // Call the checkTasks method to display the tasks as of the specified date
        checkTasks(taskList, checkedDate);
    }

}
