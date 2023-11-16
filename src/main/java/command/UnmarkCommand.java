package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * This class represents an unmark command.
 */
public class UnmarkCommand extends CrabyMessage implements CommandInterface {
    Integer UNMARK_LENGTH = 6;

    /**
     * @inheritDoc Sends the unmark message to the user and unmark the task.
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
        String checkUnmark = input.substring(UNMARK_LENGTH).trim();
        if (checkUnmark.equals("all")) {
            printUnmarkAllMessage();
            for (Task task : tasks) {
                task.setDone(false);
            }
            return;
        }
        try {
            Integer checkNum = getInteger(checkUnmark, tasks);
            if (checkNum == null) {
                return;
            }
            tasks.get(checkNum).setDone(false);
            printUnmarkMessage(tasks.get(checkNum).toString());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printUnmarkErrorMessage();
        }
    }

    private static Integer getInteger(String checkUnmark, List<Task> tasks) {
        int checkNum = (Integer.parseInt(checkUnmark)) - 1;
        if (checkNum >= tasks.size() || checkNum < 0) {
            printNumOutOfTask(tasks.size());
            return null;
        }
        return checkNum;
    }
}
