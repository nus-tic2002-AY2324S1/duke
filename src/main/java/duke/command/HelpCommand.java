package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The HelpCommand class represents a command to list all available commands with description.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Displays a list of all available commands.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Example of usage: " + COMMAND_WORD;
    public static final String COMMAND_IN_THE_LIST = "Here is a list of commands that are available:";

    /**
     * @throws InvalidArgumentException If the user input arguments are invalid.
     * @inheritDoc Executes the help command by displaying a list of available commands.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateNonEmptyKeywordArgument(keywordArgument, new HelpCommand());

        ArrayList<String> messages = new ArrayList<>();
        messages.add(COMMAND_IN_THE_LIST);
        messages.add("1. " + DatePrintCommand.COMMAND_DESCRIPTION);
        messages.add("2. " + DeadlineCommand.COMMAND_DESCRIPTION);
        messages.add("3. " + DeleteCommand.COMMAND_DESCRIPTION);
        messages.add("4. " + EventCommand.COMMAND_DESCRIPTION);
        messages.add("5. " + ExitCommand.COMMAND_DESCRIPTION);
        messages.add("6. " + FindCommand.COMMAND_DESCRIPTION);
        messages.add("7. " + HelpCommand.COMMAND_DESCRIPTION);
        messages.add("8. " + ListCommand.COMMAND_DESCRIPTION);
        messages.add("9. " + MarkCommand.COMMAND_DESCRIPTION);
        messages.add("10. " + RecurCommand.COMMAND_DESCRIPTION);
        messages.add("11. " + TodoCommand.COMMAND_DESCRIPTION);
        messages.add("12. " + UnMarkCommand.COMMAND_DESCRIPTION);
        messages.add("13. " + UpdateCommand.COMMAND_DESCRIPTION);

        ui.showResponseToUser(messages);
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
