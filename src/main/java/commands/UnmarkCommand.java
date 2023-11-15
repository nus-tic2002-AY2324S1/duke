package commands;

import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import exceptions.MissingTaskException;
import storage.Storage;
import tasks.TaskList;
import ui.UI;

public class UnmarkCommand extends Command {
    protected int item;
    protected static final int INPUT_WORDS_REQUIRED = 2;

    public UnmarkCommand(String userInput) throws DukeException {
        if (userInput.split(RegExp.SPACE_DELIMITER).length < INPUT_WORDS_REQUIRED) {
            throw new DukeException(ErrorMessages.MISSING_ITEM_NUMBER);
        }

        try {
            this.item = Integer.parseInt(userInput.split(RegExp.SPACE_DELIMITER)[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessages.INVALID_INTEGER);
        }
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
            tasks.unmarkItem(item);
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }

}
