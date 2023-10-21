package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class DeleteCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will delete the task from the list.
     *
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return;
        }
        input = input.toLowerCase().trim();
        String checkDelete = input.substring(6).trim();
        if (checkDelete.equals("all")) {
            printDeleteAllMessage();
            tasks.clear();
            return;
        }
        try {
            int checkNum = (Integer.parseInt(checkDelete)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
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
