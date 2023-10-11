package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

public class DeleteAllCommand extends CrabyMessage implements CommandInterface {

    /**
     * This method will delete all the tasks in the list.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        printDeleteAllMessage();
        tasks.clear();
        return 0;
    }
}