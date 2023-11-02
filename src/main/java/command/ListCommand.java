package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class ListCommand extends CrabyMessage implements CommandInterface {

    /**
     * Sends the list task message to the user.
     * @inheritDoc
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
