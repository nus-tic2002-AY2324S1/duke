package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {

    private boolean isExit;
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public boolean isExit() {
        return isExit;
    }
}
