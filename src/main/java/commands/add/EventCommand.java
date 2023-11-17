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
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private static final int DESCRIPTION_GROUP_CAPTURE = 1;
    private static final int FROM_DATE_GROUP_CAPTURE = 2;
    private static final int TO_DATE_GROUP_CAPTURE = 5;


    public EventCommand(String input) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.EVENT_COMMAND_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(input.toLowerCase());

        if (!matcher.matches()) {
            throw new DukeException(ErrorMessages.INVALID_EVENT_COMMAND_FORMAT);
        }

        assert matcher.groupCount() == 7 : "should have 7 capture groups based on regex";
        this.description = matcher.group(DESCRIPTION_GROUP_CAPTURE);
        try {
            this.fromDate = LocalDate.parse(matcher.group(FROM_DATE_GROUP_CAPTURE));
            this.toDate = LocalDate.parse(matcher.group(TO_DATE_GROUP_CAPTURE));
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorMessages.INVALID_DATE);
        }

        if (fromDate.isAfter(toDate)) {
            throw new DukeException(ErrorMessages.ERROR_END_DATE_BEFORE_START_DATE);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int currentSize = tasks.getTotalTasks();
        Task t = tasks.addEvent(description, fromDate, toDate);
        assert tasks.getTotalTasks() == currentSize + 1 : "event should be added successfully";
        ui.showTaskAdded(t);
    }
}
