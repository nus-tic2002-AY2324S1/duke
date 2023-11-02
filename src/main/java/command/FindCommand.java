package command;

import io.CrabyMessage;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends CrabyMessage implements CommandInterface {

    /**
     * @inheritDoc
     * Sends the list of tasks that contain the keyword to the user.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;

        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return;
        }
        String[] inputArr = input.split(" ");
        assert inputArr.length > 1;
        String keyword = inputArr[1].trim().toLowerCase();
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
