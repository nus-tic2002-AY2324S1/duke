package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class BlahCommand extends CrabyMessage implements CommandInterface {
    /**
     * @inheritDoc
     * Sends blah message to the user.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        printBlahMessage();
    }
}
