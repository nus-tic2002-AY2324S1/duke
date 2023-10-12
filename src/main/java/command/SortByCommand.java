package command;

import io.CrabyMessage;
import io.TaskStorage;
import task.Task;

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
        if (checkSort.equals("type") || checkSort.equals("t")) {
            sortByType(tasks);
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

    private static void sortByType(List<Task> tasks) {
        List<String> tmp = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            String tmp1 = task.toString();
            tmp.add(tmp1);
        }
        Collections.sort(tmp);
        printStartOfList();
        for (String s : tmp) {
            System.out.println(blank + s);
        }
        printEndOfList();
    }
}
