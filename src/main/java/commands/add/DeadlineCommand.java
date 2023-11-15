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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    protected LocalDate date;
    protected String description;
    protected static final int DESCRIPTION_GROUP_CAPTURE = 1;
    protected static final int DATE_GROUP_CAPTURE = 2;

    public DeadlineCommand(String line) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.DEADLINE_COMMAND_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(line.toLowerCase());
       
        if (!matcher.matches()){
            throw new DukeException(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT);
        }

        this.description = matcher.group(DESCRIPTION_GROUP_CAPTURE);
        this.date = LocalDate.parse(matcher.group(DATE_GROUP_CAPTURE));
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addDeadline(description, date);
        ui.showTaskAdded(t);
    }

}
