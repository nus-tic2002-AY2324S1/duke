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


    /**
     * Executes the find command by searching for tasks that contain the keyword.  and display results to the user.
     *
     * @param taskList The task list to search through.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage where tasks are persisted; not used in this command.
     * @throws DukeException If a problem occurs during the execution of the command.
     * @throws IOException   If an I/O error occurs; not expected in this command.
     */
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
