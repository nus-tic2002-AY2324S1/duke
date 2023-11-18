package commands;

import java.util.regex.Pattern;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import exceptions.EmptyListException;
import exceptions.MissingTaskException;
import storage.Storage;
import tasks.Priority;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

public class SetCommand extends Command {
    private int item;
    private Priority p;

    // expected line arg: set 2 to (high/low/medium)
    public SetCommand(String line) throws DukeException {

        if (!Pattern.matches(RegExp.SET_COMMAND_FORMAT_REGEX, line)) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }

        this.item = Integer.parseInt(line.split(RegExp.SPACE_DELIMITER)[1].trim());

        try {
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
            ui.showTaskPriority(t);
        } catch (EmptyListException e) {
            ui.showError(e.getMessage());
        } catch (MissingTaskException e) {
            ui.showError(e.getMessage());
        }
    }


}
