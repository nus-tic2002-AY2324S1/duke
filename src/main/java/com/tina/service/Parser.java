package com.tina.service;

import com.tina.command.ArchiveCommand;
import com.tina.command.ByeCommand;
import com.tina.command.Command;
import com.tina.command.CommandEnum;
import com.tina.command.DeadlineCommand;
import com.tina.command.DeleteCommand;
import com.tina.command.EventCommand;
import com.tina.command.FindCommand;
import com.tina.command.HelpCommand;
import com.tina.command.ListCommand;
import com.tina.command.MarkCommand;
import com.tina.command.ScheduleCommand;
import com.tina.command.TodoCommand;
import com.tina.command.UnmarkCommand;
import com.tina.exception.InvalidDateFormatException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidParameterException;
import com.tina.task.DeadlineTask;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;
import com.tina.task.TodoTask;
import com.zoho.hawking.HawkingTimeParser;
import com.zoho.hawking.datetimeparser.configuration.HawkingConfiguration;
import com.zoho.hawking.language.english.model.DatesFound;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Parses input to a desired object, such as command object or task object.
 * Validates the input and throw exception if there is an error.
 */
public class Parser {

    /**
     * Parses user input to command object.
     *
     * @param userInput the user input
     * @return the parsed command object
     * @throws InvalidParameterException  if input parameter is invalid
     * @throws InvalidDateFormatException if the date format is invalid
     */
    public static Command parseInputToCommand(String userInput) throws InvalidParameterException,
            InvalidDateFormatException {
        List<String> list = Arrays.asList(userInput.split(" "));
        ArrayList<String> tokens = new ArrayList<>(list);

        assert !tokens.isEmpty() : "Empty input";
        String firstToken = tokens.get(0).toUpperCase();
        Command command;

        try {
            CommandEnum commandEnum = CommandEnum.valueOf(firstToken);

            // validate the command syntax and parameter format
            Validator.validateCommand(tokens, commandEnum);
            // removes the command type and remains parameters
            assert !tokens.isEmpty() : "Empty input";
            tokens.remove(0);

            command = parseTokensToCommand(tokens, commandEnum);
        } catch (IllegalArgumentException e) {
            command = new Command() {
                @Override
                public String execute(TaskList taskList, Ui ui, Storage storage) {
                    return ui.printError();
                }
            };
        }
        return command;
    }

    /**
     * Parses one line read from storage file to a task object.
     * Storage file format:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * T | 1 | join sports club
     *
     * @param line the line read from storage file.
     * @return task parsed task object.
     * @throws InvalidFileFormatException if file format is invalid.
     */
    public static Task parseStorageToTask(String line) throws InvalidFileFormatException {
        String[] parts = line.split("\\s*\\|\\s*");
        assert parts.length > 0 : "Blank line";
        boolean isDone = parts[1].equals("X");
        switch (parts[0]) {
        case "T":
            return new TodoTask(parts[2], isDone);
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                return new DeadlineTask(parts[2], isDone, by);
            } catch (DateTimeParseException e) {
                throw new InvalidFileFormatException();
            }
        case "E":
            try {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                return new EventTask(parts[2], isDone, from, to);
            } catch (DateTimeParseException e) {
                throw new InvalidFileFormatException();
            }
        default:
            throw new InvalidFileFormatException();
        }
    }

    /**
     * Parses task list to storage file format and store in an array list.
     *
     * @param tasks the task list.
     * @return the array list.
     */
    public static ArrayList<String> parseTasksToStorage(TaskList tasks) {
        ArrayList<String> taskArray = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            taskArray.add(task.toStorage());
        }
        return taskArray;
    }

    /**
     * Parse date local date.
     *
     * @param dateString the date string.
     * @return the local date.
     * @throws InvalidDateFormatException if date format is invalid.
     */
    public static LocalDateTime parseDate(String dateString) throws InvalidDateFormatException{
        HawkingTimeParser parser = new HawkingTimeParser();
        HawkingConfiguration hawkingConfiguration = new HawkingConfiguration();
        try {
            hawkingConfiguration.setFiscalYearStart(2);
            hawkingConfiguration.setFiscalYearEnd(1);
            hawkingConfiguration.setTimeZone("GMT+08:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date referenceDate = new Date();
        DatesFound datesFound = parser.parse(dateString, referenceDate, hawkingConfiguration, "eng");
        if (datesFound.getParserOutputs().isEmpty()) {
            throw new InvalidDateFormatException();
        }
        DateTime dateTime = datesFound.getParserOutputs().get(0).getDateRange().getStart();
        return dateTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Parse tokens to command.
     *
     * @param tokens      the tokens.
     * @param commandEnum the command enum.
     * @return the parsed command.
     * @throws InvalidDateFormatException if date format is invalid.
     */
    public static Command parseTokensToCommand(ArrayList<String> tokens, CommandEnum commandEnum) throws InvalidDateFormatException {
        Command command = null;
        String taskName;
        switch (commandEnum) {
        case BYE:
            command = new ByeCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case MARK:
            command = new MarkCommand(Integer.parseInt(tokens.get(0)));
            break;
        case UNMARK:
            command = new UnmarkCommand(Integer.parseInt(tokens.get(0)));
            break;
        case DELETE:
            command = new DeleteCommand(Integer.parseInt(tokens.get(0)));
            break;
        case TODO:
            command = new TodoCommand(String.join(" ", tokens));
            break;
        case DEADLINE:
            int byIndex = tokens.indexOf("/by");
            taskName = String.join(" ", tokens.subList(0, byIndex));
            String by = String.join(" ", tokens.subList(byIndex + 1, tokens.size()));
            command = new DeadlineCommand(taskName, parseDate(by));
            break;
        case EVENT:
            int fromIndex = tokens.indexOf("/from");
            int toIndex = tokens.indexOf("/to");
            taskName = String.join(" ", tokens.subList(0, fromIndex));
            String from = String.join(" ", tokens.subList(fromIndex + 1, toIndex));
            String to = String.join(" ", tokens.subList(toIndex + 1, tokens.size()));
            command = new EventCommand(taskName, parseDate(from), parseDate(to));
            break;
        case SCHEDULE:
            command = new ScheduleCommand(parseDate(String.join(" ", tokens)));
            break;
        case ARCHIVE:
            command = new ArchiveCommand(tokens.get(0));
            break;
        case FIND:
            command = new FindCommand(String.join(" ", tokens));
            break;
        case HELP:
            command = new HelpCommand();
        default:
        }
        return command;
    }

    /**
     * Parses local date time object to a readable string format.
     *
     * @param dateTime the date time.
     * @return the string format.
     */
    public static String parseLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha");
        return dateTime.format(formatter);
    }
}
