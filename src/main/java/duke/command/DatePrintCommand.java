package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePrintCommand extends Command {
    LocalDateTime date;
    public static final String COMMAND_WORD = "dprint";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " 22/10/2023\n" +
            DATE_FORMAT_MESSAGE;
    public static final String TASKS_IN_THE_LIST = "Here are the tasks in your date filter list:";
    public static final Pattern ARG_FORMAT = Pattern.compile("(?<day>[0-9]+)/" +
            "(?<month>[0-9]+)/(?<year>[0-9]{4})");

    /**
     * Executes the command to display tasks within a specific date filter.
     *
     * @param taskList        The TaskList containing the tasks to be filtered.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     * message.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new DatePrintCommand());

        final Matcher matcher = ARG_FORMAT.matcher(keywordArgument.getArguments());
        validateDateMatcher(matcher, new DatePrintCommand(), "");

        date = Parser.constructDateTime(matcher);

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        messages.add(TASKS_IN_THE_LIST);
        for (int i = 0; i <= tasks.size() - 1; i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (areDatesEqual(date, deadline.getByDateTime())) {
                    messages.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (areDatesEqual(date, event.getFromDateTime()) || areDatesEqual(date, event.getToDateTime())) {
                    messages.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                }
            }
        }
        ui.showResponseToUser(messages);
    }

    /**
     * Compares two LocalDateTime objects to check if they have the same year, month, and day.
     *
     * @param d1 The first LocalDateTime object to be compared.
     * @param d2 The second LocalDateTime object to be compared.
     * @return true if the year, month, and day are equal; false otherwise.
     */
    private boolean areDatesEqual(LocalDateTime d1, LocalDateTime d2) {
        if (d1.getYear() != d2.getYear()) {
            return false;
        } else if (d1.getMonth() != d2.getMonth()) {
            return false;
        } else {
            return d1.getDayOfMonth() == d2.getDayOfMonth();
        }
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string demonstrating how to use the command.
     */
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
