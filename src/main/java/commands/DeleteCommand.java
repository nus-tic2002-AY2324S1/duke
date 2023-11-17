package commands;

import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import exceptions.MissingTaskException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    protected int item;

    public DeleteCommand(String input) throws DukeException {
        if (!Pattern.matches(RegExp.DELETE_COMMAND_FORMAT_REGEX, input.toLowerCase())) {
            throw new DukeException(ErrorMessages.INVALID_DELETE_COMMAND_FORMAT);
        }

        this.item = Integer.parseInt(input.split(RegExp.SPACE_DELIMITER)[1]);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} deletes the specified {@code task} object from the user's
     * {@code tasks} and uses the {@code ui} object to return the error or a successful message back to
     * the user.
     *
     * @param storage This parameter is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task t = tasks.deleteItem(item);
            ui.showTaskDeleted(t, tasks.getTotalTasks());
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }

}
