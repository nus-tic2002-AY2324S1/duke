package command;

import io.CrabyMessage;
import io.Sort;
import task.Task;

import java.util.List;

public class SortCommand extends CrabyMessage implements CommandInterface {

    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printEmptyListForAllCommand(input);
            return 0;
        }
        List <String> allowedSort = List.of("type", "t", "typerevert", "tr", "date", "d");
        String checkSort = input.substring(4).trim().toLowerCase();
        if (allowedSort.contains(checkSort)) {
            Sort sort = new Sort();
            sort.sort(checkSort, tasks);
            printListMessage(tasks);
            return 0;
        } else {
            printSortErrorMessage();
        }
        return 0;
    }
}

