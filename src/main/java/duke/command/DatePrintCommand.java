package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * The DatePrintCommand class represents a command that prints deadlines or events occurring on a specific date.
 */
public class DatePrintCommand extends Command {
    LocalDateTime date;
    public static final String COMMAND_WORD = "dprint";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Prints a list of tasks scheduled for the " +
            "specified date.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter: DATE " + DATE_FORMAT_MESSAGE + ".\n" +
            "Example of usage: " + COMMAND_WORD + " 22/10/2023";
    public static final String TASKS_IN_THE_LIST = "Here are the tasks in your date filter list:";
    public static final String MESSAGE_LIST_IS_EMPTY = "There are no items in the filter list for the given date!";


    /**
     * @param taskList        The TaskList containing the tasks to be filtered.
     * @param ui              The user interface for displaying messages to the user.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     * @inheritDoc Executes the command to display tasks within a specific date filter.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new DatePrintCommand());

        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(keywordArgument.getArguments());
        validateDateMatcher(dateMatcher, new DatePrintCommand(), "");

        date = Parser.constructDateTime(dateMatcher);
        ArrayList<Task> foundTasks = taskList.getTasksByDate(date);

        if (foundTasks.isEmpty()) {
            ui.showResponseToUser(MESSAGE_LIST_IS_EMPTY);
            return;
        }

        processResponseMessage(ui, foundTasks, TASKS_IN_THE_LIST);
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
