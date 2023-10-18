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
     * @return 0.
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return 0;
        }
        input = input.toLowerCase().trim();
        String checkDelete = input.substring(6).trim();
        if (checkDelete.equals("all")) {
            printDeleteAllMessage();
            tasks.clear();
            return 0;
        }
        try {
            int checkNum = (Integer.parseInt(checkDelete)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                printNumOutOfTask(tasks.size());
                return 0;
            }
            printDeleteMessage(tasks.get(checkNum).toString());
            tasks.remove(checkNum);
            printCountTask(tasks.size());
        } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
            printDeleteErrorMessage();
        }
        return 0;
    }
}
