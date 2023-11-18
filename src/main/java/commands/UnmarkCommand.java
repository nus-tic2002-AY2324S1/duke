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

public class UnmarkCommand extends Command {
    protected int item;

    public UnmarkCommand(String input) throws DukeException {
        if(!Pattern.matches(RegExp.UNMARK_COMMAND_FORMAT_REGEX, input)){
            throw new DukeException(ErrorMessages.INVALID_UNMARK_COMMAND_FORMAT);
        };
        this.item = Integer.parseInt(input.split(RegExp.SPACE_DELIMITER)[1]);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} unmarks the specified {@code task} object from the
     * user's {@code tasks}.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task t = tasks.unmarkItem(item);
            ui.showUnmarkedTask(t);
        } catch (MissingTaskException | DukeException e) {
            ui.showError(e.getMessage());
        }
    }

}
