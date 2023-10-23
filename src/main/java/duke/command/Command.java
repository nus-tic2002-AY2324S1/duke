package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
