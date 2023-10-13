package command;

import io.CrabyMessage;
import io.HelpMessage;
import task.Task;

import java.util.List;

public class HelpCommand extends HelpMessage implements CommandInterface {

    @Override
    public short handleCommand(String input, List<Task> tasks) {
        printHelpMessage();
        return 0;
    }
}
