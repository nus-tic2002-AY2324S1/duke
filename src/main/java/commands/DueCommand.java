package commands;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.LocalDate;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

/**
 * Validates user input and extracts out the due date in the format of YYYY-MM-DD e.g. 2023-10-22
 * <p>
 * A <code>DueCommand</code> object stores a date and an arraylist of tasks due on that date.
 * 
 */

public class DueCommand extends Command {
    protected LocalDate date;
    protected ArrayList<Task> tasksDue;
    protected static final int INPUT_WORDS_REQUIRED = 2;

    public DueCommand(String line) throws DukeException {
        if (line.split(RegExp.SPACE_DELIMITER).length < INPUT_WORDS_REQUIRED) {
            throw new DukeException(ErrorMessages.MISSING_DATE);
        }
        if (line.split(RegExp.SPACE_DELIMITER).length > INPUT_WORDS_REQUIRED) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }

        String userDate = line.split(RegExp.SPACE_DELIMITER)[1];
        if (!Pattern.matches(RegExp.STRICT_DATE_REGEX, userDate)) {
            throw new DukeException(ErrorMessages.INVALID_DATE_FORMAT);
        }

        this.date = LocalDate.parse(userDate);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} gets all related tasks due on the particular date and
     * prints it on the user interface.
     * 
     * @param storage is not used in this implementation.
     */

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        this.tasksDue = tasks.getTasksDue(this.date);
        ui.showTasksDue(this.tasksDue, this.date);
    }
}
