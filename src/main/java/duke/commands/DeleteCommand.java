package duke.commands;

import duke.constants.ErrorMessages;
import duke.constants.RegExp;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.MissingTaskException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DeleteCommand extends Command {

    protected int item;
    protected static final int INPUT_WORDS_REQUIRED = 2;

    public DeleteCommand(String userInput) throws DukeException {

        // guard condition
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
     * This implementation of {@code execute} deletes the specified {@code task} object from the user's
     * {@code tasks}.
     *
     * @param storage This parameter is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.deleteItem(item);
        } catch (EmptyListException e) {
            ui.showError(e.getMessage());
            return;
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
            return;
        } finally {
            ui.printMessage(
                    "That's one less thing to do! You now have " + tasks.getTotalTasks() + " tasks left.");

        }
    }

}
