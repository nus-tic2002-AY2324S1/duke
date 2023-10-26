package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class MarkCommand extends CrabyMessage implements CommandInterface {

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
            Integer checkNum = getInteger(input, tasks);
            if (checkNum == null) {
                return;
            }
            tasks.get(checkNum).setIsDone(true);
            printMarkMessage(tasks.get(checkNum).toString());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printMarkNumFormatExceptionMessage();
        }
    }

    private static Integer getInteger(String input, List<Task> tasks) {
        input = input.trim();
        String checkMark = input.substring(4).trim();
        int checkNum = (Integer.parseInt(checkMark)) - 1;
        if (checkNum >= tasks.size() || checkNum < 0) {
            printNumOutOfTask(tasks.size());
            return null;
        }
        return checkNum;
    }
}
