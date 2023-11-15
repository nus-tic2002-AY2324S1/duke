package commands;

import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;
import storage.Storage;
import tasks.TaskList;
import ui.UI;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    protected int item;

    public DeleteCommand(String input) throws DukeException {
        if(!Pattern.matches(RegExp.DELETE_COMMAND_FORMAT_REGEX,input.toLowerCase())){
            throw new DukeException(ErrorMessages.INVALID_DELETE_COMMAND_FORMAT);
        }

        this.item = Integer.parseInt(input.split(RegExp.SPACE_DELIMITER)[1]);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} deletes the specified {@code task} object from the user's
     * {@code tasks}.
     *
     * @param storage This parameter is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.deleteItem(item);
        } catch (EmptyListException | MissingTaskException e ) {
            ui.showError(e.getMessage());
            return;
        } finally {
            ui.printMessage(
                    "That's one less thing to do! You now have " + tasks.getTotalTasks() + " tasks left.");

        }
    }

}
