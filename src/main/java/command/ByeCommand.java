package command;

import task.Task;

import java.util.List;

public class ByeCommand extends CrabyMessage implements CommandInterface {

    @Override
    public void handleCommand(String input, List<Task> tasks) {
        System.out.println(blank + "Bye Amber ♡, hope to see you again soon!");
        System.out.println(line + System.lineSeparator() + crab);
    }
}
