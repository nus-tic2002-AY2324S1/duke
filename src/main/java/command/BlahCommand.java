package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class BlahCommand extends CrabyMessage implements CommandInterface {
    /**
     * This method will print out the blah message.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        printBlahMessage();
    }
}
