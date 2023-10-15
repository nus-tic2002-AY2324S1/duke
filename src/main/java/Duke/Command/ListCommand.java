package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

import java.util.List;

public class ListCommand extends Command {

    @Override
    public void execute() {
        List<Task> taskList = Duke.taskList;
        printList(taskList);
    }

    /**
     * Displays the list of user tasks.
     *
     * @param taskList The array of user tasks.
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


}
