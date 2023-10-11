package command;

import io.CrabyMessage;
import task.Task;

import java.util.Collections;
import java.util.List;

public class ListCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will print out all the tasks in the list.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return 0.
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printStartOfList();
            printEmptyList();
            printEndOfList();
            return 0;
        }
        printStartOfList();
        printListMessage(tasks);
        printEndOfList();
        return 0;
    }
}
