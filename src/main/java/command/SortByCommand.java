package command;

import io.CrabyMessage;
import io.Sort;
import task.Task;

import java.util.List;

public class SortByCommand extends CrabyMessage implements CommandInterface {
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printStartOfList();
            printEmptyList();
            printEndOfList();
            return 0;
        }
        String checkSort = input.substring(6).trim().toLowerCase();
        if (checkSort.equals("type") || checkSort.equals("t")
                || checkSort.equals("type1") || checkSort.equals("t1")
                || checkSort.equals("date") || checkSort.equals("d")) {
            sortBy(checkSort, tasks);
            return 0;
        } else {
            printSortByErrorMessage();
        }

        return 0;
    }

    private static void sortBy(String input, List<Task> tasks) {
        Sort sort = new Sort();
        sort.sort(input, tasks);
        printListMessage(tasks);
    }
}

