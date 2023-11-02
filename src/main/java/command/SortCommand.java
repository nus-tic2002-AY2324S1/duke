package command;

import io.CrabyMessage;
import io.Sort;
import task.Task;

import java.util.List;

/**
 * SortCommand class is a class that handle the sort command.
 * It implements the CommandInterface.
 * It has a method to handle the sort command.
 */
public class SortCommand extends CrabyMessage implements CommandInterface {

    /**
     * Sends the sorted list task message to the user and sort the list.
     * @inheritDoc
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return;
        }
        List<String> allowedSort = List.of("type", "t", "type-r", "tr", "date", "d");
        String checkSort = input.substring(4).trim().toLowerCase();
        if (allowedSort.contains(checkSort)) {
            Sort sort = new Sort();
            sort.sort(checkSort, tasks);
            printListMessage(tasks);
        } else {
            printSortErrorMessage();
        }
    }
}

