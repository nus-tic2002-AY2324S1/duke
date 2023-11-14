package duke.commands.add;

import duke.commands.Command;
import duke.constants.RegExp;
import duke.constants.ErrorMessages;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class EventCommand extends Command {
    public String description;
    public LocalDate fromDate;
    public LocalDate toDate;

    public EventCommand(String input) throws DukeException {

        if (input.indexOf("/from") == -1 || input.indexOf("/to") == -1) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }
        // since the program hardcoded +5, need to check that /from has no trailing letters after it
        if (input.charAt(input.indexOf("/from") + 5) != ' ') {
            throw new DukeException(ErrorMessages.TRAILING_LETTERS_BEHIND_FROM);
        }
        // since the program hardcoded +5, need to check that /from has no trailing letters after it
        if (input.charAt(input.indexOf("/to") + 3) != ' ') {
            throw new DukeException(ErrorMessages.TRAILING_LETTERS_BEHIND_TO);
        }

        String userFrom = input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim();
        String userTo = input.substring(input.indexOf("/to") + 3).trim();

        if (!Pattern.matches(RegExp.DATE_REGEX, userFrom)
                || !Pattern.matches(RegExp.DATE_REGEX, userFrom)) {
            throw new DukeException(ErrorMessages.INVALID_DATE_FORMAT);
        }

        this.description = input.substring(5, input.indexOf("/from")).trim();
        if (description.length() == 0) {
            throw new DukeException(ErrorMessages.MISSING_EVENT_DESCRIPTION);
        }

        this.fromDate = LocalDate.parse(userFrom);
        this.toDate = LocalDate.parse(userTo);
        if (fromDate.isAfter(toDate)) {
            throw new DukeException(ErrorMessages.END_DATE_EARLIER);
        }

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addEvent(description, fromDate, toDate);
        ui.showTaskAdded(t);
    }
}
