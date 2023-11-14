package duke.commands;

import java.util.ArrayList;
import java.util.regex.Pattern;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Validates user input and extracts out the due date in the format of YYYY-MM-DD e.g. 2023-10-22
 * <p>
 * A <code>DueCommand</code> object stores a date and an arraylist of tasks due on that date.
 * 
 */

public class DueCommand extends Command {
    LocalDate date;
    public ArrayList<Task> tasksDue;

    public DueCommand(String line) throws DukeException {
        if (line.split(" ").length < 2) {
            throw new DukeException("Missing date!");
        } else if (line.split(" ").length > 2) {
            throw new DukeException("Please follow the correct format.");
        } else {
            String userDate = line.split(" ")[1];
            String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

            if (!Pattern.matches(regex, userDate)) {
                throw new DukeException("Please provide the date in this format: YYYY-MM-DD");
            } else {
                this.date = LocalDate.parse(userDate);
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
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
