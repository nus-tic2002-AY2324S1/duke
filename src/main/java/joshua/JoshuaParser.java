package joshua;

import commands.Command;
import commands.MarkCommand;
import commands.UnmarkCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.TodoCommand;
import commands.DeadlineCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ByeCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import exceptions.InvalidCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public static final Pattern DATE_REGEX_PATTERN = Pattern.compile("(\\d{1,2}\\/\\d{1,2}\\/\\d{4}) (\\d{4})");

    private final String PARSED_DATE_STRING = "d/M/yyyy HHmm";
    private final String FORMATTED_DATE_STRING = "d MMM yyyy, hh:mm a";
    private final DateTimeFormatter PARSED_DATE_FORMATTER = DateTimeFormatter.ofPattern(PARSED_DATE_STRING);
    private final DateTimeFormatter FORMATTED_DATE_FORMATTER = DateTimeFormatter.ofPattern(FORMATTED_DATE_STRING);

    public JoshuaParser() {

    }

    public Command parse(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if(!matcher.matches()) {
            return new HelpCommand();
        }

        String commandWord = matcher.group("commandWord");
        String args = matcher.group("arguments").trim();

        switch(commandWord) {
        case MarkCommand.COMMAND_WORD:
            return prepareMark(args);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(args);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(args);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(args);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(args);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(args);

        case FindCommand.COMMAND_WORD:
            return new FindCommand(args);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new InvalidCommand(InvalidCommand.INVALID_COMMAND_MESSAGE);
        }
    }

    public Command prepareMark(String commandArgs) {
        int taskNum = parseTaskNumber(commandArgs);
        if (taskNum == -1) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new MarkCommand(taskNum);
    }

    public Command prepareUnmark(String commandArgs) {
        int taskNum = parseTaskNumber(commandArgs);
        if (taskNum == -1) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new UnmarkCommand(taskNum);
    }

    public Command prepareDelete(String commandArgs) {
        int taskNum = parseTaskNumber(commandArgs);
        if (taskNum == -1) {
            return new InvalidCommand("Please enter an integer number.");
        }
        return new DeleteCommand(taskNum);
    }

    private int parseTaskNumber(String commandArgs) {
        int taskNum = -1;
        try {
            taskNum = Integer.parseInt(commandArgs);
        } catch (NumberFormatException ignored) {
        }
        return taskNum;
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

        try {
            by = parseDateTime(by);
        } catch (InvalidCommandException e) {
            return new InvalidCommand(e.getMessage());
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

        try {
            from = parseDateTime(from);
            to = parseDateTime(to);
        } catch (InvalidCommandException e) {
            return new InvalidCommand(e.getMessage());
        }

        return new EventCommand(desc, from ,to);
    }

    /**
     * Takes in a string and checks if it is in the accepted date format (d/M/yyyy HHmm)
     * then parses it into a LocalDateTime object before finally formatting it as a string
     *
     * @param dateStr The date entered by the user
     * @return Date and time in the format: dd MMM yyyy, hh:mm a
     * @throws InvalidCommandException If user entered date does not follow the specified format
     */
    private String parseDateTime(String dateStr) throws InvalidCommandException {
        Matcher dateMatcher = DATE_REGEX_PATTERN.matcher(dateStr);
        if(!dateMatcher.matches()) {
            throw new InvalidCommandException("Please follow this format for entering a date:\n" + PARSED_DATE_STRING);
        }

        LocalDateTime parsedDateTime = LocalDateTime.parse(dateStr, PARSED_DATE_FORMATTER);
        String formattedDateTime = parsedDateTime.format(FORMATTED_DATE_FORMATTER);
        return formattedDateTime;
    }
}
