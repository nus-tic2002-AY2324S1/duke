package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * This class represents a unmark command.
 */
public class UnmarkCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will mark the task as done.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
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
        try {
            int tmp = input.indexOf("k");
            assert tmp != -1;
            String checkMark = input.substring(tmp + 1).trim();
            int checkNum = (Integer.parseInt(checkMark)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                printNumOutOfTask(tasks.size());
                return;
            }
            tasks.get(checkNum).setIsDone(false);
            printUnmarkMessage(tasks.get(checkNum).toString());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printUnmarkNumFormatExceptionMessage();
        }
    }
}
