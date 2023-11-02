package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

/**
 * SwitchCommand class is a class that handle the switch command.
 * It implements the CommandInterface.
 * It has a method to handle the switch command.
 */
public class SwitchCommand extends CrabyMessage implements CommandInterface {

    /**
     * @inheritDoc
     * Sends switch message to the user and switch to another checklist.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        // switch to another checklist
        printSwitchMessage();
        task.Craby.crabySystem(false);
    }
}
