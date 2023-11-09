package com.tina.service;

import com.joestelmach.natty.DateGroup;
import com.tina.command.*;
import com.tina.exception.*;
import com.tina.task.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The Parser class.
 * Parse input to a desired object, such as command object or task object.
 * Valid the input and throw exception if there is an error.
 */
public class Parser {

    /**
     * Parse user input to command object.
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
        CommandEnum commandEnum = null;
        Command command = null;

        try {
            commandEnum = CommandEnum.valueOf(firstToken);
            int taskNumber;
            String taskName;

            // validate the command syntax and parameter format
            Validator.validateCommand(tokens, commandEnum);

            // removes the command type and remains parameters
            tokens.remove(0);

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
                    taskName = String.join(" ", tokens.subList(1, byIndex));
                    String by = String.join(" ", tokens.subList(byIndex + 1, tokens.size()));
                    command = new DeadlineCommand(taskName, parseDate(by));
                    break;
                case EVENT:
                    int fromIndex = tokens.indexOf("/from");
                    int toIndex = tokens.indexOf("/to");
                    taskName = String.join(" ", tokens.subList(1, fromIndex));
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
                default:
            }

        } catch (IllegalArgumentException e) {
            command = new Command(CommandEnum.UNKNOWN) {
                @Override
                public void execute(TaskList taskList, Ui ui, Storage storage) {
                    ui.printError();
                }
            };
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
        return command;
    }

    /**
     * Parse one line read from storage file to a task object
     * Storage file format:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * T | 1 | join sports club
     *
     * @param line the line read from storage file
     * @return task parsed task object
     * @throws InvalidFileFormatException if file format is invalid
     */
    public static Task parseStorageToTask(String line) throws InvalidFileFormatException {
        String[] parts = line.split("\\s*\\|\\s*");
        boolean isDone = parts[1].equals("X");
        switch (parts[0]) {
            case "T":
                return new TodoTask(parts[2], isDone);
            case "D":
                return new DeadlineTask(parts[2], isDone, LocalDate.parse(parts[3]));
            case "E":
                return new EventTask(parts[2], isDone, LocalDate.parse(parts[3]),
                        LocalDate.parse(parts[4]));
            default:
                throw new InvalidFileFormatException();
        }
    }

    /**
     * Parse task list to storage file format and store in an array list.
     *
     * @param tasks the task list
     * @return the array list
     */
    public static ArrayList<String> parseTasksToStorage(TaskList tasks) {
        ArrayList<String> taskArray = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            taskArray.add(task.toStorage());
        }
        return taskArray;
    }

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
}
