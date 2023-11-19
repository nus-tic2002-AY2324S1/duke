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
import commands.ScheduleCommand;
import commands.ByeCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.GTWCommand;
import exceptions.InvalidCommandException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the commands input by the user into a format understood by the program.
 */
public class JoshuaParser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern DEADLINE_ARGUMENT_FORMAT = Pattern.compile("(?<desc>.*)/by(?<by>.*)");
    public static final Pattern EVENT_ARGUMENT_FORMAT = Pattern.compile("(?<desc>.*)/from(?<from>.*)/to(?<to>.*)");
    public static final Pattern DATETIME_REGEX_PATTERN = Pattern.compile("(\\d{1,2}\\/\\d{1,2}\\/\\d{4}) (\\d{4})");
    private final String PARSED_DATETIME_STRING = "d/M/yyyy HHmm";
    private final DateTimeFormatter PARSED_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(PARSED_DATETIME_STRING);
    private final DateTimeFormatter FORMATTED_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public JoshuaParser() {

    }

    /**
     * Returns a Command object based on the command input by the user.
     *
     * @param userInput The command input by the user.
     * @return Command object with respect to userInput.
     */
    public Command parse(String userInput) {
        assert userInput.equals(userInput.toLowerCase()) : "User input should be in lower case: " + userInput;

        // parse() method adapted from
        // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java#L56
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if(userInput.equals("global thermonuclear war")) {
            return new GTWCommand();
        }
        else if(!matcher.matches()) {
            return new HelpCommand();
        }

        String commandWord = matcher.group("commandWord");
        String args = matcher.group("arguments").trim();

        switch(commandWord) {
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            return prepareTaskOperationCommand(commandWord, args);

        case "list":
            return new ListCommand();

        case "todo":
            return prepareTodo(args);

        case "deadline":
            return prepareDeadline(args);

        case "event":
            return prepareEvent(args);

        case "find":
            return new FindCommand(args);

        case "schedule":
            return prepareSchedule(args);

        case "bye":
            return new ByeCommand();

        case "help":
            return new HelpCommand();

        default:
            return new InvalidCommand(InvalidCommand.INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     *  Returns a Command object of a task operation (ie mark, unmark, delete)
     *  which is instantiated with the task numbers given in commandArgs
     *
     * @param commandWord The command input by the user.
     * @param commandArgs The arguments of the command.
     * @return A Command object.
     */
    private Command prepareTaskOperationCommand(String commandWord, String commandArgs) {
        int[] taskNums;
        try {
            taskNums = parseTaskNumbers(commandArgs);
        } catch (InvalidCommandException e) {
            return new InvalidCommand(e.getMessage());
        }

        switch (commandWord) {
        case "mark":
            return new MarkCommand(taskNums);
        case "unmark":
            return new UnmarkCommand(taskNums);
        case "delete":
            return new DeleteCommand(taskNums);
        default:
            return new InvalidCommand(InvalidCommand.INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Returns an integer array of the task numbers to be operated on.
     * The method takes in a string and splits it by the commas into a String array.
     * Each String in the array is parsed into an integer and added to an integer array.
     *
     * @param commandArgs The arguments of the command.
     * @return An integer array.
     * @throws InvalidCommandException If the string cannot be parsed into an integer.
     */
    private int[] parseTaskNumbers(String commandArgs) throws InvalidCommandException {
        String[] taskNumsStrArray = commandArgs.split(",");
        int[] taskNums = new int[taskNumsStrArray.length];
        try {
            for (int i = 0; i < taskNumsStrArray.length; i++) {
                taskNums[i] = Integer.parseInt(taskNumsStrArray[i].trim());
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Please enter an integer number.");
        }
        return taskNums;
    }

    /**
     * Returns a TodoCommand object, instantiating it with the task description
     * as parsed from the commandArgs input by the user.
     *
     * @param commandArgs The arguments of the command.
     * @return A TodoCommand or InvalidCommand object.
     */
    private Command prepareTodo(String commandArgs) {
        String desc = commandArgs.trim();
        if (desc.isEmpty()) {
            return new InvalidCommand("Enter a description for your todo.");
        }
        return new TodoCommand(desc);
    }

    /**
     * Returns a DeadlineCommand object, instantiating it with the task description and "by" date
     * as parsed from the commandArgs input by the user.
     *
     * @param commandArgs The arguments of the command.
     * @return A DeadlineCommand or InvalidCommand object.
     */
    private Command prepareDeadline(String commandArgs) {
        assert commandArgs.equals(commandArgs.trim()) : "String was not trimmed as expected: " + commandArgs;

        Matcher matcher = DEADLINE_ARGUMENT_FORMAT.matcher(commandArgs);
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

    /**
     * Returns an EventCommand object, instantiating it with the task description, "from" date and "to" date
     * as parsed from the commandArgs input by the user.
     *
     * @param commandArgs The arguments of the command.
     * @return An EventCommand or InvalidCommand object.
     */
    private Command prepareEvent(String commandArgs) {
        assert commandArgs.equals(commandArgs.trim()) : "String was not trimmed as expected: " + commandArgs;

        Matcher matcher = EVENT_ARGUMENT_FORMAT.matcher(commandArgs);
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
     * Returns a ScheduleCommand object, instantiating it with the date string
     * as parsed from the commandArgs input by the user.
     *
     * @param commandArgs The arguments of the command.
     * @return A ScheduleCommand or InvalidCommand object.
     */
    private Command prepareSchedule(String commandArgs) {
        if (commandArgs.isEmpty()) {
            return new InvalidCommand("Enter a date to view schedule.");
        }

        String targetDate;
        try {
            targetDate = parseDateTime(commandArgs);
        } catch (InvalidCommandException e) {
            return new InvalidCommand(e.getMessage());
        }

        return new ScheduleCommand(targetDate);
    }

    /**
     * Returns a string of the parsed date. It will check if dateStr is in the accepted date format (d/M/yyyy HHmm)
     * then parse it into a LocalDateTime object before finally formatting it as a string.
     *
     * @param dateStr The date entered by the user
     * @return Date and time in the format: dd MMM yyyy, hh:mm a
     * @throws InvalidCommandException If user entered date does not follow the specified format
     */
    public String parseDateTime(String dateStr) throws InvalidCommandException {
        Matcher dateTimeMatcher = DATETIME_REGEX_PATTERN.matcher(dateStr);
        if(!dateTimeMatcher.matches()) {
            throw new InvalidCommandException("Please follow this format for entering a date:\n" + PARSED_DATETIME_STRING);
        }

        String formattedDateTime;
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateStr, PARSED_DATETIME_FORMATTER);
            formattedDateTime = parsedDateTime.format(FORMATTED_DATETIME_FORMATTER);
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Entered date is invalid. Please check your input.");
        }

        return formattedDateTime;
    }
}
