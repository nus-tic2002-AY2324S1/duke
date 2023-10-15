package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

public class DeleteCommand extends Command {
    private final int itemIndex;

    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task.removeTask();
        Task deletedTask = Duke.taskList.remove(itemIndex);
        UserInterface.MessageDisplay.deleteMessage(deletedTask);
    }
}
