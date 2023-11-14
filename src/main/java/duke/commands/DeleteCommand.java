package duke.commands;

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
            throw new DukeException("Missing item number!");
        }
        try {
            this.item = Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Ensure the item number is a valid integer!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation of {@code execute} deletes the specified {@code task} object from the
     * user's {@code tasks}
     * 
     * @param storage is not used in this implementation.
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
        }
        ui.printMessage(
                "That's one less thing to do! You now have " + tasks.totalTasks + " tasks left.");

    }

}
