package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the abstract Command class that serves as the base for all command classes.
 */
public abstract class Command {
    public static final String DESC_ERR_MESSAGE = "OOPS!!! The \"Parameter\" of a \"%s\" command cannot be empty :(";
    public static final String DESC_NEED_BLANK_ERR_MESSAGE = "OOPS!!! Please leave the \"Parameter\" blank for the " +
            "\"%s\" command.";
    public static final String DATE_TIME_FORMAT_MESSAGE = "{dd/mm/yyyy hhmm}";
    public static final String TIME_FORMAT_MESSAGE = "{hhmm}";
    public static final String DATE_FORMAT_MESSAGE = "{dd/mm/yyyy}";
    public static final String SUB_ARG_ERR_MESSAGE = "OOPS!!! The \"%s\" of a \"%s\" cannot be empty :(";
    public static final String DATE_TIME_ERR_MESSAGE = "OOPS!!! The %s format of a \"%s\" is invalid :(";
    public static final Pattern DATE_TIME_ARG_FORMAT = Pattern.compile("(?<day>[0-9]{2})/" +
            "(?<month>[0-9]{2})/(?<year>[0-9]{4})\\s(?<timeArgument>\\w.*)");
    public static final Pattern DATE_ARG_FORMAT = Pattern.compile("(?<day>[0-9]{2})/" +
            "(?<month>[0-9]{2})/(?<year>[0-9]{4})");
    public static final Pattern TIME_ARG_FORMAT = Pattern.compile("(?<hour>[0-9]{2})(?<minute>[0-9]{2})");
    private static boolean isExit = false;

    /**
     * Executes the command based on the provided task list, user interface, storage, and user input arguments.
     * Subclasses must implement this method to define specific command behavior.
     *
     * @param taskList        The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage for saving and loading task data.
     * @param keywordArgument The user input argument containing command details.
     * @throws InvalidArgumentException If the input arguments are invalid or do not match the command requirements.
     */
    public abstract void executeCommand(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException;

    /**
     * Sets the exit flag to indicate that the program should exit.
     */
    protected static void setExit() {
        isExit = true;
    }

    /**
     * Throws an InvalidArgumentException if the arguments in the given UserKeywordArgument are empty.
     *
     * @param keywordArgument The UserKeywordArgument containing the keyword and arguments.
     * @param command         The Command associated with the keyword.
     * @throws InvalidArgumentException If the arguments are empty, an exception is thrown with an error message.
     */
    public void validateKeywordArgument(UserKeywordArgument keywordArgument, Command command)
            throws InvalidArgumentException {
        if (keywordArgument.getArguments().isEmpty()) {
            String errMsg = String.format(DESC_ERR_MESSAGE, command.getCommandWord());
            throw new InvalidArgumentException(Message.concat(errMsg, command.getExampleUsage()));
        }
    }

    /**
     * Throws an InvalidArgumentException if the arguments in the given UserKeywordArgument are not empty.
     *
     * @param keywordArgument The UserKeywordArgument containing the keyword and arguments.
     * @param command         The Command associated with the keyword.
     * @throws InvalidArgumentException If the arguments are not empty, an exception is thrown with an error message.
     */
    public void validateNonEmptyKeywordArgument(UserKeywordArgument keywordArgument, Command command)
            throws InvalidArgumentException {
        if (!keywordArgument.getArguments().isEmpty()) {
            String errMsg = String.format(DESC_NEED_BLANK_ERR_MESSAGE, command.getCommandWord());
            throw new InvalidArgumentException(Message.concat(errMsg, command.getExampleUsage()));
        }
    }

    /**
     * Throws an InvalidArgumentException if the given Matcher does not match the expected date pattern.
     *
     * @param matcher The Matcher object to be checked against a specific pattern.
     * @param command The Command associated with the pattern being checked.
     * @param addOn   Additional information to be included in the error message (can be empty).
     * @throws InvalidArgumentException If the Matcher does not match the pattern, an exception is thrown with an
     *                                  error message.
     */
    public void validateDateMatcher(Matcher matcher, Command command, String addOn)
            throws InvalidArgumentException {
        String commandWord;
        if (addOn.isEmpty()) {
            commandWord = command.getCommandWord();
        } else {
            commandWord = addOn + " " + command.getCommandWord();
        }

        if (!matcher.matches()) {
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "date", commandWord);
            throw new InvalidArgumentException(Message.concat(errMsg, command.getExampleUsage()));
        }
    }

    /**
     * Throws an InvalidArgumentException if the given Matcher does not match the expected time pattern.
     *
     * @param matcher The Matcher object to be checked against a specific time pattern.
     * @param command The Command associated with the pattern being checked.
     * @param addOn   Additional information to be included in the error message (can be empty).
     * @throws InvalidArgumentException If the Matcher does not match the time pattern, an exception is thrown with
     *                                  an error message.
     */
    public void validateTimeMatcher(Matcher matcher, Command command, String addOn) throws InvalidArgumentException {
        String commandWord;
        if (addOn.isEmpty()) {
            commandWord = command.getCommandWord();
        } else {
            commandWord = addOn + " " + command.getCommandWord();
        }

        if (!matcher.matches()) {
            String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "time", commandWord);
            throw new InvalidArgumentException(Message.concat(errMsg, command.getExampleUsage()));
        }
    }

    /**
     * Validates a Matcher object against a specific pattern and throws an exception if it does not match.
     *
     * @param matcher The Matcher object to be validated against a pattern.
     * @param command The Command associated with the pattern being checked.
     * @param addOn   Additional information to be included in the error message.
     * @throws InvalidArgumentException If the Matcher does not match the pattern, an exception is thrown with an
     *                                  error message.
     */
    public void validateArgumentMatcher(Matcher matcher, Command command, String addOn)
            throws InvalidArgumentException {
        if (!matcher.matches()) {
            String errMsg = String.format(SUB_ARG_ERR_MESSAGE, addOn, command.getCommandWord());
            throw new InvalidArgumentException(Message.concat(errMsg, command.getExampleUsage()));
        }
    }

    /**
     * Checks if the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Processes and displays the list of found tasks
     *
     * @param ui         The user interface object used to display the response.
     * @param foundTasks The list of tasks found based on a search criterion
     */
    public void processResponseMessage(Ui ui, ArrayList<Task> foundTasks, String respMessage) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(respMessage);
        for (int i = 0; i < foundTasks.size(); i++) {
            Task task = foundTasks.get(i);
            messages.add(i + 1 + "." + task.toString());
        }
        ui.showResponseToUser(messages);
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string for the command.
     */
    public abstract String getExampleUsage();

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    public abstract String getCommandWord();

}
