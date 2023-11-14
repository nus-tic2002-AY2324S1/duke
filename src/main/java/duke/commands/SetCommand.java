package duke.commands;

import java.util.regex.Pattern;
import duke.constants.Constant;
import duke.constants.ErrorMessages;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.MissingTaskException;
import duke.storage.Storage;
import duke.tasks.Priority;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class SetCommand extends Command {

    int item;
    Priority p;

    // expected line arg: set 2 to (high/low/medium)
    public SetCommand(String line) throws DukeException {

        // check if format of input is correct
        if (Pattern.matches(Constant.DATE_REGEX, line.toLowerCase())) {
            try {
                this.item = Integer.parseInt(line.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(ErrorMessages.INVALID_INTEGER);
            } finally {
                String taskPriority = line.split(" ")[3].trim().toUpperCase();
                try {
                    this.p = Priority.valueOf(taskPriority);
                } catch (IllegalArgumentException e) {
                    throw new DukeException(ErrorMessages.INVALID_PRIORITY);
                }
            }
        } else {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }



    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} sets the task item to the {@code Priority} attribute
     * specified when first creating this {@code SetCommand} object.
     * 
     * 
     * @param storage is not used in this implementation.
     */

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task t = tasks.setPriority(item, p);
            ui.showTaskPrioritySet(t);
        } catch (EmptyListException e) {
            ui.showError(e.getMessage());
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }


}
