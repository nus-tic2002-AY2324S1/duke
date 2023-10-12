package command;

import io.CrabyMessage;
import io.Sort;
import io.TaskStorage;
import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
                || checkSort.equals("type1") || checkSort.equals("t1")) {
            sortByType(checkSort, tasks);
            return 0;
        } else if (checkSort.equals("date") || checkSort.equals("d")) {
            sortByDate(tasks);
            return 0;
        } else {
            printSortByErrorMessage();
        }

        return 0;
    }

    private void sortByDate(List<Task> tasks) {
        System.out.println("This function is not available yet.");
    }
    private static void sortByType(String input, List<Task> tasks) {
        Sort sort = new Sort();
        sort.sort(input, tasks);
        printListMessage(tasks);
    }
}

