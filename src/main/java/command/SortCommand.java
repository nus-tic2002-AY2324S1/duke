package command;

import io.CrabyMessage;
import io.Sort;
import task.Task;

import java.util.List;

/**
 * SortCommand class is a class that handles the sort command.
 * It implements the CommandInterface.
 * It has a method to handle the sort command.
 */
public class SortCommand extends CrabyMessage implements CommandInterface {
    Integer SORT_LENGTH = 4;

    /**
     * @inheritDoc Sends the sorted list task message to the user and sort the list.
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
        String checkSort = input.substring(SORT_LENGTH).trim().toLowerCase();
        if (allowedSort.contains(checkSort)) {
            Sort sort = new Sort();
            sort.sort(checkSort, tasks);
            printListMessage(tasks);
        } else {
            printSortErrorMessage();
        }
    }
}

