package commands.add;

import commands.Command;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class EventCommand extends Command {
    protected String description;
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected static final int DESCRIPTION_GROUP_CAPTURE = 1;
    protected static final int FROM_DATE_GROUP_CAPTURE = 2;
    protected static final int TO_DATE_GROUP_CAPTURE = 5;


    public EventCommand(String input) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.EVENT_COMMAND_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(input.toLowerCase());

        if (!matcher.matches()) {
            throw new DukeException(ErrorMessages.INVALID_EVENT_COMMAND_FORMAT);
        }

        this.description = matcher.group(DESCRIPTION_GROUP_CAPTURE);
        this.fromDate = LocalDate.parse(matcher.group(FROM_DATE_GROUP_CAPTURE));
        this.toDate = LocalDate.parse(matcher.group(TO_DATE_GROUP_CAPTURE));

        if (fromDate.isAfter(toDate)) {
            throw new DukeException(ErrorMessages.ERROR_END_DATE_BEFORE_START_DATE);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addEvent(description, fromDate, toDate);
        ui.showTaskAdded(t);
    }
}
