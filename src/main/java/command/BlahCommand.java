package command;

import task.Task;

import java.util.List;

public class BlahCommand extends CrabyMessage implements CommandInterface{
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        System.out.println(blank + "Oops!!! I'm sorry, but I don't know what that means ☘");
        System.out.println(line);
    }
}
