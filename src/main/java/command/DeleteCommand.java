package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * DeleteCommand class is a class that handles the delete command.
 * It implements the CommandInterface.
 * It has a method to handle the delete command.
 */
public class DeleteCommand extends CrabyMessage implements CommandInterface {

    Integer DELETE_LENGTH = 6;

    /**
     * @inheritDoc Sends the deleted message to the user.
     * This method will delete the task from the list.
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
        assert input.length() >= DELETE_LENGTH;
        String checkDelete = input.substring(DELETE_LENGTH).trim();
        if (checkDelete.equals("all")) {
            printDeleteAllMessage();
            tasks.clear();
            return;
        }
        try {
            Integer checkNum = getInteger(tasks, checkDelete);
            if (checkNum == null) {
                return;
            }
            printDeleteMessage(tasks.get(checkNum).toString());
            tasks.remove(checkNum);
            printCountTask(tasks.size());
        } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
            printDeleteErrorMessage();
        }
    }

    private static Integer getInteger(List<Task> tasks, String checkDelete) {
        int checkNum = (Integer.parseInt(checkDelete)) - 1;
        if (checkNum >= tasks.size() || checkNum < 0) {
            printNumOutOfTask(tasks.size());
            return null;
        }
        return checkNum;
    }
}
