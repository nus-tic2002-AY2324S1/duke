package duke.commands.add;

import duke.commands.Command;
import duke.constants.RegExp;
import duke.constants.ErrorMessages;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    LocalDate date;
    String description;
    static final int BY_STRING_LENGTH = 3;
    static final String BY_FORMAT = "/by";
    static final char EMPTY_CHAR = ' ';

    public DeadlineCommand(String line) throws DukeException {
        // deadline buy food /by 2023-10-05 & format accepted yyyy-mm-dd
        if (line.indexOf(BY_FORMAT) == -1) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }
        // since the program hardcoded +3, need to check that /by has no trailing y's
        if (line.charAt(line.indexOf(BY_FORMAT) + BY_STRING_LENGTH) != EMPTY_CHAR) {
            throw new DukeException(ErrorMessages.TRAILING_LETTERS_BEHIND_BY);
        }

        this.description = line.substring(8, line.indexOf(BY_FORMAT)).trim();
        if (description.length() == 0) {
            throw new DukeException(ErrorMessages.MISSING_TASK_DESCRIPTION);
        }

        String userDate = line.substring(line.indexOf(BY_FORMAT) + BY_STRING_LENGTH).trim();
        if (!Pattern.matches(RegExp.DATE_REGEX, userDate)) {
            throw new DukeException(ErrorMessages.INVALID_DATE_FORMAT);
        }

        this.date = LocalDate.parse(userDate);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addDeadline(description, date);
        ui.showTaskAdded(t);
    }

}
