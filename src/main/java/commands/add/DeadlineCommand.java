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
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    private LocalDate date;
    private String description;
    private static final int DESCRIPTION_GROUP_CAPTURE = 1;
    private static final int DATE_GROUP_CAPTURE = 2;

    public DeadlineCommand(String line) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.DEADLINE_COMMAND_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(line.toLowerCase());

        if (!matcher.matches()) {
            throw new DukeException(ErrorMessages.INVALID_DEADLINE_COMMAND_FORMAT);
        }

        assert matcher.groupCount() == 4 : "should have 4 capture groups based on regexp";
        this.description = matcher.group(DESCRIPTION_GROUP_CAPTURE);

        try {
            this.date = LocalDate.parse(matcher.group(DATE_GROUP_CAPTURE));
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorMessages.INVALID_DATE);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int currentSize = tasks.getTotalTasks();
        Task t = tasks.addDeadline(description, date);
        assert tasks.getTotalTasks() == currentSize + 1 : "deadline should be added successfully";
        ui.showTaskAdded(t);
    }

}
