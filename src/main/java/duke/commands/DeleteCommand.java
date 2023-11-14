package duke.commands;

import duke.constants.Constant;
import duke.constants.ErrorMessages;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.MissingTaskException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DeleteCommand extends Command {

    int item;

    public DeleteCommand(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException(ErrorMessages.MISSING_ITEM_NUMBER);
        } else {
            try {
                this.item = Integer.parseInt(userInput.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(ErrorMessages.INVALID_INTEGER);
            }
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
