package commands.add;

import commands.Command;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    LocalDate date;
    String description;
    protected static final int BY_STRING_LENGTH = 3;
    protected static final String BY_FORMAT = "/by";
    protected static final int DEADLINE_STRING_END_INDEX = 8;

    public DeadlineCommand(String line) throws DukeException {
        // deadline buy food /by 2023-10-05 & format accepted yyyy-mm-dd
        if (!Pattern.matches(RegExp.DEADLINE_COMMAND_FORMAT_REGEX, line.toLowerCase())){
            throw new DukeException(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT);
        }

        String userDate = line.substring(line.indexOf(BY_FORMAT) + BY_STRING_LENGTH).trim();
        this.description = line.substring(DEADLINE_STRING_END_INDEX, line.indexOf(BY_FORMAT)).trim();
        this.date = LocalDate.parse(userDate);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addDeadline(description, date);
        ui.showTaskAdded(t);
    }

}
