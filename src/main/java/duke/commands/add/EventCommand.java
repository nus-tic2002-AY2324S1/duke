package duke.commands.add;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.regex.*;
import java.time.LocalDate;

public class EventCommand extends Command {
    public String description;
    public LocalDate fromDate;
    public LocalDate toDate;

    public EventCommand(String input) throws DukeException {

        if (input.indexOf("/from") == -1 || input.indexOf("/to") == -1) {
            throw new DukeException("Please follow the correct format.");
        }

        // since the program hardcoded +5, need to check that /from has no trailing
        // letters after it
        if (input.charAt(input.indexOf("/from") + 5) != ' ')
            throw new DukeException("Ensure you have no trailing letters behind /from.");
        // since the program hardcoded +5, need to check that /from has no trailing
        // letters after it
        if (input.charAt(input.indexOf("/to") + 3) != ' ')
            throw new DukeException("Ensure you have no trailing letters behind /to.");

        String userFrom = input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim();
        String userTo = input.substring(input.indexOf("/to") + 3).trim();
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

        if (!Pattern.matches(regex, userFrom) || !Pattern.matches(regex, userFrom)) {
            throw new DukeException("Please ensure both Start and End Date follows this format: YYYY-MM-DD");
        }

        this.description = input.substring(5, input.indexOf("/from")).trim();
        if (description.length() == 0)
            throw new DukeException("Oops, missing event description!");

        this.fromDate = LocalDate.parse(userFrom);
        this.toDate = LocalDate.parse(userTo);
        if (fromDate.isAfter(toDate)) {
            throw new DukeException("Your End Date should be later than your Start Date.");
        }

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addEvent(description, fromDate, toDate);
        ui.showTaskAdded(t);
    }
}
