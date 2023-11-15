package commands.add;

import java.time.LocalDate;
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
    protected static final int TODO_STRING_END_INDEX = 4;
    protected static final String BETWEEN_STRING = "/between";

    // todo colllect certificate /between 2023-10-08 /and 2023-10-10
    public ToDoWithinPeriodCommand(String line) throws DukeException{
        Pattern pattern = Pattern.compile(RegExp.TODO_COMMAND_WITHIN_PERIOD_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(line);

        if(!matcher.matches()){
            throw new DukeException(ErrorMessages.INVALID_TODO_WITHIN_PERIOD_FORMAT);
        };

        this.description = matcher.group(1).trim();
        LocalDate firstDate =  LocalDate.parse(matcher.group(2));
        LocalDate secondDate = LocalDate.parse(matcher.group(5));

        if(firstDate.isAfter(secondDate)){
            throw new DukeException(ErrorMessages.ERROR_END_DATE_BEFORE_START_DATE);
        }
        this.from = firstDate;
        this.to = secondDate;
    }
    /**
     * {@inheritDoc}
     * <p>
     * This implementation of {@code execute} adds a to do task with period to
     * the user's {@code tasks}.
     * 
     * @param storage is not used in this implementation.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = tasks.addToDoWithinPeriod(description, to, from);
        ui.showTaskAdded(t);
    }
    
}
