package amebot.commands;

import amebot.common.Messages;
import amebot.tasks.Task;

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
