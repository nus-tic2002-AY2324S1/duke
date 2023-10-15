package commands;

import common.Messages;

public class DeleteCommand extends Command {
    public DeleteCommand(int index) {
        if (index > -1 && index <= tasks.size()) {
            logs.add(tasks.get(index - 1).getTask());
            tasks.remove(index - 1);
            saveOutput();
        }
    }

    public void saveOutput() {
        logs.add(Messages.SUCCESS_REMOVE_MESSAGE);
        logs.add(tasks.size() + Messages.CURRENT_LIST);
    }
}
