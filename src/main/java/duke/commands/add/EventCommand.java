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
    static final int TO_STRING_LENGTH = 3;
    static final int FROM_STRING_LENGTH = 5;
    static final String TO_STRING = "/to";
    static final String FROM_STRING = "/from";
    static final char EMPTY_CHAR = ' ';
    static final int EVENT_STRING_LENGTH = 5;

    public EventCommand(String input) throws DukeException {

        if (input.indexOf(FROM_STRING) == -1 || input.indexOf(TO_STRING) == -1) {
            throw new DukeException(ErrorMessages.INVALID_COMMAND_FORMAT);
        }
        // since the program hardcoded +5, need to check that /from has no trailing letters after it
        if (input.charAt(input.indexOf(FROM_STRING) + FROM_STRING_LENGTH) != EMPTY_CHAR) {
            throw new DukeException(ErrorMessages.TRAILING_LETTERS_BEHIND_FROM);
        }
        if (input.charAt(input.indexOf(TO_STRING) + TO_STRING_LENGTH) != EMPTY_CHAR) {
            throw new DukeException(ErrorMessages.TRAILING_LETTERS_BEHIND_TO);
        }

        String userFrom = input
                .substring(input.indexOf(FROM_STRING) + FROM_STRING_LENGTH, input.indexOf(TO_STRING)).trim();
        String userTo = input.substring(input.indexOf(TO_STRING) + TO_STRING_LENGTH).trim();

        if (!Pattern.matches(RegExp.DATE_REGEX, userFrom) || !Pattern.matches(RegExp.DATE_REGEX, userFrom)) {
            throw new DukeException(ErrorMessages.INVALID_DATE_FORMAT);
        }

        this.description = input.substring(EVENT_STRING_LENGTH, input.indexOf(FROM_STRING)).trim();
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
