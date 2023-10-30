package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

    /**
     * Represents a command to unmark a task as not done in the task list.
     */
public class UnmarkCommand extends Command{
    private int index;

        /**
         * Constructs an UnmarkCommand with the given index.
         *
         * @param index The index of the task to be unmarked.
         */
        public UnmarkCommand(int index){
        this.index = index;
    }

    /**
     * Executes the unmark command, marking the specified task as not done in the task list and updating the storage.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for input and output.
     * @param storage  The storage for reading and writing data.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try{
            Task unmarkedTask = taskList.unmarkTaskAsNotDone(index);
            storage.save(taskList);
            UI.showTaskUnmarkedAsNotDone(unmarkedTask);
        } catch (DukeException | IOException e) {
           UI.showError(e.getMessage());
        }
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return Always returns false, as unmarking a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
