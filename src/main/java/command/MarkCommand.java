package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class MarkCommand extends CrabyMessage implements CommandInterface {
    Integer MARK_LENGTH = 4;

    /**
     * @inheritDoc Sends a mark message to the user and mark the task as done.
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
        String checkMark = input.substring(MARK_LENGTH).trim();
        if (checkMark.equals("all")) {
            printMarkAllMessage();
            for (Task task : tasks) {
                task.setDone(true);
            }
            return;
        }
        try {
            Integer checkNum = getInteger(checkMark, tasks);
            if (checkNum == null) {
                return;
            }
            tasks.get(checkNum).setDone(true);
            printMarkMessage(tasks.get(checkNum).toString());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printMarkErrorMessage();
        }
    }

    private static Integer getInteger(String checkMark, List<Task> tasks) {
        int checkNum = (Integer.parseInt(checkMark)) - 1;
        if (checkNum >= tasks.size() || checkNum < 0) {
            printNumOutOfTask(tasks.size());
            return null;
        }
        return checkNum;
    }
}
