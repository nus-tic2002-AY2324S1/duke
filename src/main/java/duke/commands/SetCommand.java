package duke.commands;

import java.util.regex.Pattern;
import duke.constants.ErrorMessages;
import duke.constants.RegExp;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.MissingTaskException;
import duke.storage.Storage;
import duke.tasks.Priority;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class SetCommand extends Command {
    protected int item;
    protected Priority p;

    // expected line arg: set 2 to (high/low/medium)
    public SetCommand(String line) throws DukeException {

        if (!Pattern.matches(RegExp.SET_COMMAND_FORMAT_REGEX, line.toLowerCase())) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }

        try {
            this.item = Integer.parseInt(line.split(RegExp.SPACE_DELIMITER)[1]);
            String taskPriority = line.split(RegExp.SPACE_DELIMITER)[3].trim().toUpperCase();
            this.p = Priority.valueOf(taskPriority);
        } catch (IllegalArgumentException e) {
            throw new DukeException(ErrorMessages.INVALID_PRIORITY);
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
