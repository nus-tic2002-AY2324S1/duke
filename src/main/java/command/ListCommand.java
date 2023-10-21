package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class ListCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will print out all the tasks in the list.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;

        if (tasks.isEmpty()) {
            printEmptyList();
            return;
        }
        printListMessage(tasks);
    }
}
