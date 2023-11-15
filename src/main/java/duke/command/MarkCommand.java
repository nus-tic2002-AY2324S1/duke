package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Represents a command to mark a task as done by its index.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the given index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking a specified task as done and displaying a confirmation message.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for input and output.
     * @param storage  The storage for reading and writing data (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task markAsDOneTask = taskList.markTaskAsDone(index);
            Storage.save(taskList);
            UI.showTaskMarkedAsDone(markAsDOneTask);
        } catch (DukeException | IOException e) {
            UI.showMessage(e.getMessage());
        }
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return Always returns false, as marking a task as done does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return true;
    }
}
