package amebot.commands;

import amebot.common.Messages;

public class UpdateCommand extends Command {
    public UpdateCommand(int index, String commandType) {
        if (index > 0) {
            if (commandType.equalsIgnoreCase("select")) {
                tasks.get(index - 1).setSelectStatus();
                logs.add(Messages.STATUS_SELECTED);
            } else {
                tasks.get(index - 1).setUnselectStatus();
                logs.add(Messages.STATUS_UNSELECTED);
            }

            logs.add(tasks.get(index - 1).getTask());
        }
    }
}
