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
     * @return
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int checkNum = (Integer.parseInt(checkMark)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                printNumOutOfTask(tasks.size());
                return 0;
            }
            tasks.get(checkNum).setIsDone(true);
            printMarkMessage(tasks.get(checkNum).toString());
            return 0;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            printMarkNumFormatExceptionMessage();
        }
        return 0;
    }
}
