package nus.duke.parser;

import nus.duke.commands.ByeCommand;
import nus.duke.commands.Command;
import nus.duke.commands.DateCommand;
import nus.duke.commands.DeadlineCommand;
import nus.duke.commands.DeleteCommand;
import nus.duke.commands.EventCommand;
import nus.duke.commands.ListCommand;
import nus.duke.commands.MarkCommand;
import nus.duke.commands.TodoCommand;
import nus.duke.commands.UnmarkCommand;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.UnknownCommandDukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {
    private static final String DATE_PATTERN_INPUT = "yyyy-M-d";
    private static final String TIME_PATTERN_INPUT = "HHmm";
    private static final String DATETIME_PATTERN_INPUT = DATE_PATTERN_INPUT + " " + TIME_PATTERN_INPUT;

    public static Command parse(String fullCommand) throws DukeException {
        String trimmedFullCommand = fullCommand.trim();
        String commandName = trimmedFullCommand.split(" ", -1)[0];
        String commandArgs = trimmedFullCommand.substring(commandName.length()).trim();
        switch (commandName) {
        case "bye":
            return new ByeCommand(commandArgs);
        case "date":
            return new DateCommand(commandArgs);
        case "deadline":
            return new DeadlineCommand(commandArgs);
        case "delete":
            return new DeleteCommand(commandArgs);
        case "event":
            return new EventCommand(commandArgs);
        case "list":
            return new ListCommand(commandArgs);
        case "mark":
            return new MarkCommand(commandArgs);
        case "todo":
            return new TodoCommand(commandArgs);
        case "unmark":
            return new UnmarkCommand(commandArgs);
        default:
            throw new UnknownCommandDukeException("Input: " + fullCommand);
        }
    }

    public static LocalDate parseUserDate(String text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN_INPUT, Locale.ROOT);
        return LocalDate.parse(text, dateTimeFormatter);
    }

    public static LocalDateTime parseUserDateTime(String text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN_INPUT, Locale.ROOT);
        return LocalDateTime.parse(text, dateTimeFormatter);
    }

    public static LocalDateTime parseUserRelativeDateTime(LocalDateTime referenceTime, String text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN_INPUT, Locale.ROOT);
        try {
            return LocalDateTime.parse(text, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN_INPUT, Locale.ROOT);
            LocalTime time = LocalTime.parse(text, dateTimeFormatter);
            return referenceTime.with(time);
        }
    }
}
