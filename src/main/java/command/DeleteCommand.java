package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * DeleteCommand class is a class that handle the delete command.
 * It implements the CommandInterface.
 * It has a method to handle the delete command.
 */
public class DeleteCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will delete the task from the list.
     *
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return;
        }
        input = input.toLowerCase().trim();
        assert input.length() >= 6;
        String checkDelete = input.substring(6).trim();
        if (checkDelete.equals("all")) {
            printDeleteAllMessage();
            tasks.clear();
            return;
        }
        try {
            int checkNum = (Integer.parseInt(checkDelete)) - 1;
            boolean isNumOutOfTask = checkNum >= tasks.size() || checkNum < 0;
            if (isNumOutOfTask) {
                printNumOutOfTask(tasks.size());
                return;
            }
            printDeleteMessage(tasks.get(checkNum).toString());
            tasks.remove(checkNum);
            printCountTask(tasks.size());
        } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
            printDeleteErrorMessage();
        }
    }
}
