package joshua;

import commands.Command;
import commands.MarkCommand;
import commands.UnmarkCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.TodoCommand;
import commands.DeadlineCommand;
import commands.EventCommand;
import commands.ByeCommand;
import commands.HelpCommand;
import commands.InvalidCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoshuaParser {
    /**
     * Pattern to identify command word and the following arguments.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern DEADLINE_ARGUMENT_FORMAT = Pattern.compile("(?<desc>.*)/by(?<by>.*)");
    public static final Pattern EVENT_ARGUMENT_FORMAT = Pattern.compile("(?<desc>.*)/from(?<from>.*)/to(?<to>.*)");

    /**
     * Pattern to identify date format if user has entered a date.
     */
    public static final Pattern DATE_FORMAT = Pattern.compile("(\\\\d{1,2}/\\\\d{1,2}/\\\\d{4}) (\\\\d{4})");

    public JoshuaParser() {

    }

    public Command parse(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if(!matcher.matches()) {
            return new HelpCommand();
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch(commandWord) {
        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new InvalidCommand(InvalidCommand.INVALID_COMMAND_MESSAGE);
        }
    }

    public Command prepareMark(String commandArgs) {
        int taskNum = -1;
        commandArgs = commandArgs.trim();
        try {
            taskNum = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new MarkCommand(taskNum);
    }

    public Command prepareUnmark(String commandArgs) {
        int taskNum = -1;
        commandArgs = commandArgs.trim();
        try {
            taskNum = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new UnmarkCommand(taskNum);
    }

    public Command prepareDelete(String commandArgs) {
        int taskNum = -1;
        commandArgs = commandArgs.trim();
        try {
            taskNum = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new DeleteCommand(taskNum);
    }

    public Command prepareTodo(String commandArgs) {
        String desc = commandArgs.trim();
        if (desc.isEmpty()) {
            return new InvalidCommand("Enter a description for your todo.");
        }
        return new TodoCommand(desc);
    }

    public Command prepareDeadline(String commandArgs) {
        Matcher matcher = DEADLINE_ARGUMENT_FORMAT.matcher(commandArgs.trim());
        if(!matcher.matches()) {
            return new InvalidCommand("Please follow this command format for \"deadline\":\n" +
                    "deadline <description> /by <date>");
        }

        String desc = matcher.group("desc").trim();
        String by = matcher.group("by").trim();

        if (desc.isEmpty()) {
            return new InvalidCommand("Enter a description for your deadline.");
        }
        if (by.isEmpty()) {
            return new InvalidCommand("Enter the /by parameter for your deadline.");
        }
        return new DeadlineCommand(desc, by);
    }

    public Command prepareEvent(String commandArgs) {
        Matcher matcher = EVENT_ARGUMENT_FORMAT.matcher(commandArgs.trim());
        if(!matcher.matches()) {
            return new InvalidCommand("Please follow this command format for \"event\":\n" +
                    "event <description> /from <date> /to <date>");
        }

        String desc = matcher.group("desc").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();

        if (desc.isEmpty()) {
            return new InvalidCommand("Enter a description for your event.");
        }
        if (from.isEmpty()) {
            return new InvalidCommand("Enter the /from parameter for your event.");
        }
        if (to.isEmpty()) {
            return new InvalidCommand("Enter the /to parameter for your event.");
        }
        return new EventCommand(desc, from ,to);
    }

    private static ArrayList<String> stringToArrayList(String str) {
        String[] strArray = str.split("\\s+"); // Split on any number of whitespaces
        List<String> strList = new ArrayList<>(Arrays.asList(strArray));
        return new ArrayList<>(strList);
    }
}
