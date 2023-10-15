package commands;

import common.Messages;
import tasks.Task;

public class ListCommand extends Command {
    public ListCommand() {
        if (tasks.isEmpty()) {
            logs.add(Messages.EMPTY_LIST);
        } else {
            saveLogs();
        }
    }

    public void saveLogs() {
        int itemNum = 1;

        logs.add(Messages.FULL_LIST);
        for (Task task : tasks) {
            logs.add(itemNum + ". " + task.getTask());
            itemNum++;
        }
    }
}
