package command;

import io.CrabyMessage;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends CrabyMessage implements CommandInterface {
    private static final Integer FIND_LENGTH = 4;

    /**
     * @inheritDoc Sends the list of tasks that contain the keyword to the user.
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
        String keyword = input.substring(FIND_LENGTH).trim();
        List<String> listFound = new ArrayList<>();
        for (Task task : tasks) {
            String tmpAdd = task.toString();
            boolean isContain = tmpAdd.toLowerCase().contains(keyword);
            if (isContain) {
                listFound.add(tmpAdd);
            }
        }
        if (listFound.isEmpty()) {
            printNoMatchingTasks(keyword);
        } else {
            printFindMessage(listFound, keyword);
        }
    }
}
