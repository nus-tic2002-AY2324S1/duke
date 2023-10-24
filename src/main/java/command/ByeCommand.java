package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * ByeCommand class is a class that handle the bye command.
 * It implements the CommandInterface.
 * It has a method to handle the bye command.
 */
public class ByeCommand extends CrabyMessage implements CommandInterface {
    /**
     * This method will print out the bye message.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        printByeMessage();
    }
}
