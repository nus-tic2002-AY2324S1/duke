package command;

import io.HelpMessage;
import task.Task;

import java.util.List;
/**
 * This class will print out the help message.
 */
public class HelpCommand extends HelpMessage implements CommandInterface {

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

        printHelpMessage();
    }
}
