package command;

import io.CrabyMessage;
import io.TaskStorage;
import task.Task;

import java.util.List;

public class SwitchCommand extends CrabyMessage implements CommandInterface {

    @Override
    public void handleCommand(String input, List<Task> tasks) {
        // switch to another list
        printSwitchMessage();
        task.Craby.crabySystem(false);
    }
}
