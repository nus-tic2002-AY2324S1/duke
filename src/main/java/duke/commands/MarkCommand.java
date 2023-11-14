package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.MissingTaskException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class MarkCommand extends Command {
    int item;

    public MarkCommand(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException("Missing item number!");
        } else {
            try {
                this.item = Integer.parseInt(userInput.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Ensure the item number is a valid integer!");
            }
        }

    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} marks the specified {@code task} object from the user's
     * {@code tasks} list to done.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task t = tasks.markItem(item);
            ui.showMarkedTask(t);
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }

}
