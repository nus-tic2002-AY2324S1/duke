package Duke.Command;

import Duke.Duke;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

public class MarkAsInCompletedCommand extends Command {
    private final int itemIndex;

    public MarkAsInCompletedCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task task = Duke.taskList.get(itemIndex);
        if (!task.isCompleted()) {
            UserInterface.MessageDisplay.notMark(task.getTaskName());
        } else {
            task.markAsNotCompleted();
            UserInterface.MessageDisplay.unCompleteMessage(Duke.taskList, itemIndex);
        }
    }
}
