package com.tina.service;

import com.joestelmach.natty.DateGroup;
import com.tina.command.*;
import com.tina.exception.InvalidDateFormatException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidParameterException;
import com.tina.task.DeadlineTask;
import com.tina.task.EventTask;
import com.tina.task.Task;
import com.tina.task.TaskList;
import com.tina.task.TodoTask;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public static Command parseInputToCommand(String userInput)
            throws InvalidParameterException, InvalidDateFormatException {
        List<String> list = Arrays.asList(userInput.split(" "));
        ArrayList<String> tokens = new ArrayList<>(list);

        String firstToken = tokens.get(0).toUpperCase();
        Command command;

        try {
            CommandEnum commandEnum = CommandEnum.valueOf(firstToken);

            // validate the command syntax and parameter format
            Validator.validateCommand(tokens, commandEnum);
            // removes the command type and remains parameters
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
        boolean isDone = parts[1].equals("X");
        switch (parts[0]) {
        case "T":
            return new TodoTask(parts[2], isDone);
        case "D":
            try {
                LocalDate by = LocalDate.parse(parts[3]);
                return new DeadlineTask(parts[2], isDone, by);
            } catch (DateTimeParseException e) {
                throw new InvalidFileFormatException();
            }
        case "E":
            try {
                LocalDate from = LocalDate.parse(parts[3]);
                LocalDate to = LocalDate.parse(parts[4]);
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
    public static LocalDate parseDate(String dateString) throws InvalidDateFormatException {
        com.joestelmach.natty.Parser parser = new com.joestelmach.natty.Parser();

        List<DateGroup> groups = parser.parse(dateString);
        if (groups.size() != 1) {
            throw new InvalidDateFormatException();
        }

        List<Date> dates = groups.get(0).getDates();
        if (dates.isEmpty()) {
            throw new InvalidDateFormatException();
        }

        Date date = dates.get(0);
        // parse Date object to LocalDate object
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Parse tokens to command.
     *
     * @param tokens      the tokens.
     * @param commandEnum the command enum.
     * @return the parsed command.
     * @throws InvalidDateFormatException if date format is invalid.
     */
    public static Command parseTokensToCommand(ArrayList<String> tokens, CommandEnum commandEnum)
            throws InvalidDateFormatException {
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
}
