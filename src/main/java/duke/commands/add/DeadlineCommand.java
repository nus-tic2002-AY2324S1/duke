package duke.commands.add;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.regex.*;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    LocalDate date;
    String description;

    public DeadlineCommand(String line) throws DukeException {
        // deadline buy food /by 2023-10-05 & format accepted yyyy-mm-dd
        if (line.indexOf("/by") == -1)
            throw new DukeException("Please follow the correct format.");

        // since the program hardcoded +3, need to check that /by has no trailing y's
        if (line.charAt(line.indexOf("/by") + 3) != ' ')
            throw new DukeException("Ensure you have no trailing letters behind /by.");

        this.description = line.substring(8, line.indexOf("/by")).trim();
        if (description.length() == 0)
            throw new DukeException("Oops, missing task description!");

        String userDate = line.substring(line.indexOf("/by") + 3).trim();
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

        if (!Pattern.matches(regex, userDate))
            throw new DukeException("Please provide the date in this format: YYYY-MM-DD");
        this.date = LocalDate.parse(userDate);

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addDeadline(description, date);
        ui.showTaskAdded(t);
    }

}
