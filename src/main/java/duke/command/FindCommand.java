package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        TaskList foundTasks = taskList.findTasksByKeyword(keyword);
        UI.showSearchResults(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }
}
