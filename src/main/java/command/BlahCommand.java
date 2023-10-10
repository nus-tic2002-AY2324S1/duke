package command;

import task.Task;

import java.util.List;

public class BlahCommand extends CrabyMessage implements CommandInterface{
    /**
     * This method will print out the blah message.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        System.out.println(blank + "Oops!!! I'm sorry, but I don't know what that means ☘");
        System.out.println(line);
        return 0;
    }
}
