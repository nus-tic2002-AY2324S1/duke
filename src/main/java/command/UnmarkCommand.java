package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * This class represents an unmark command.
 */
public class UnmarkCommand extends CrabyMessage implements CommandInterface {

    /**
     * @inheritDoc
     * Sends the unmark message to the user and unmark the task.
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
            Integer checkNum = getInteger(input, tasks);
            if (checkNum == null) {
                return;
            }
            tasks.get(checkNum).setDone(false);
            printUnmarkMessage(tasks.get(checkNum).toString());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printUnmarkErrorMessage();
        }
    }

    private static Integer getInteger(String input, List<Task> tasks) {
        input = input.trim();
        String checkMark = input.substring(6).trim();
        int checkNum = (Integer.parseInt(checkMark)) - 1;
        if (checkNum >= tasks.size() || checkNum < 0) {
            printNumOutOfTask(tasks.size());
            return null;
        }
        return checkNum;
    }
}
