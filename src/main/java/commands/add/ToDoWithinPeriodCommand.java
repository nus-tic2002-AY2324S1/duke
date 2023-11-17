package commands.add;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.Command;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

public class ToDoWithinPeriodCommand extends Command {
    protected LocalDate from;
    protected LocalDate to;
    protected String description;
    protected static final String BETWEEN_STRING = "/between";
    protected static final int DESCRIPTION_GROUP_CAPTURE = 1;
    protected static final int FROM_DATE_GROUP_CAPTURE = 2;
    protected static final int TO_DATE_GROUP_CAPTURE = 5;

    // todo colllect certificate /between 2023-10-08 /and 2023-10-10
    public ToDoWithinPeriodCommand(String line) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.TODO_COMMAND_WITHIN_PERIOD_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new DukeException(ErrorMessages.INVALID_TODO_WITHIN_PERIOD_FORMAT);
        } ;

        assert matcher.groupCount() == 7 : "should have 7 capture groups based on regex";
        this.description = matcher.group(DESCRIPTION_GROUP_CAPTURE).trim();

        try {
            this.from = LocalDate.parse(matcher.group(FROM_DATE_GROUP_CAPTURE));
            this.to = LocalDate.parse(matcher.group(TO_DATE_GROUP_CAPTURE));
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorMessages.INVALID_DATE);
        }

        if (from.isAfter(to)) {
            throw new DukeException(ErrorMessages.ERROR_END_DATE_BEFORE_START_DATE);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} adds a to do task with period to the user's {@code tasks}.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int currentSize = tasks.getTotalTasks();
        Task t = tasks.addToDoWithinPeriod(description, from, to);
        assert tasks.getTotalTasks() == currentSize + 1 : "todo should be added successfully";
        ui.showTaskAdded(t);
    }

}
