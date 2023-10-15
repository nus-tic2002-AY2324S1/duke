package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

public class MarkAsCompletedCommand extends Command {
    private final int itemIndex;

    public MarkAsCompletedCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task task = Duke.taskList.get(itemIndex);
        if (task.isCompleted()) {
            UserInterface.MessageDisplay.alreadyMark(task.getTaskName());
        } else {
            task.markAsCompleted();
            UserInterface.MessageDisplay.completeMessage(Duke.taskList, itemIndex);
        }
    }

}
