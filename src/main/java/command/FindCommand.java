package command;

import io.CrabyMessage;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will find the user keyword from the list and print out.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return 0.
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return 0;
        }
        String[] inputArr = input.split(" ");
        String keyword = inputArr[1].trim().toLowerCase();
        List<String> listFound = new ArrayList<>();
        for (Task task : tasks) {
            String tmpAdd = task.toString();
            String tmpCheck = tmpAdd.toLowerCase();
            if (tmpCheck.contains(keyword)) {
                listFound.add(tmpAdd);
            }
        }
        if (listFound.isEmpty()) {
            printNoMatchingTasks();
        } else {
            printFindMessage(listFound);
        }
        return 0;
    }
}
