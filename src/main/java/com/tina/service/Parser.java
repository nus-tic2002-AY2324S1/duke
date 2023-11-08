package com.tina.service;

import com.tina.command.*;
import com.tina.exception.*;
import com.tina.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static Command parseInputToCommand(String userInput) throws InvalidParameterException, InvalidDateFormatException {
        List<String> list = Arrays.asList(userInput.split(" "));
        ArrayList<String> tokens = new ArrayList<>(list);
        String firstToken = tokens.get(0).toUpperCase();
        CommandEnum commandEnum;
        Command command = null;

        try {
            commandEnum = CommandEnum.valueOf(firstToken);
        } catch (IllegalArgumentException e) {
            commandEnum = null;
        }

        if (commandEnum != null) {
            int taskNumber;
            String taskName;

            switch (commandEnum) {
                case BYE:
                    validateSingleCommand(tokens, CommandEnum.BYE);
                    command = new ByeCommand();
                    break;
                case LIST:
                    validateSingleCommand(tokens, CommandEnum.LIST);
                    command = new ListCommand();
                    break;
                case MARK:
                    taskNumber = validateTaskNumber(tokens, CommandEnum.MARK);
                    command = new MarkCommand(taskNumber);
                    break;
                case UNMARK:
                    taskNumber = validateTaskNumber(tokens, CommandEnum.UNMARK);
                    command = new UnmarkCommand(taskNumber);
                    break;
                case DELETE:
                    taskNumber = validateTaskNumber(tokens, CommandEnum.DELETE);
                    command = new DeleteCommand(taskNumber);
                    break;
                case TODO:
                    if (tokens.size() == 1) {
                        throw new InvalidParameterException(CommandEnum.TODO);
                    }
                    tokens.remove(0);
                    taskName = String.join(" ", tokens);
                    command = new TodoCommand(taskName);
                    break;
                case DEADLINE:
                    int byIndex = tokens.indexOf("/by");
                    if (tokens.size() == 1 || byIndex == -1 || byIndex == tokens.size() - 1 ) {
                        throw new InvalidParameterException(CommandEnum.DEADLINE);
                    }
                    taskName = String.join(" ", tokens.subList(1, byIndex));
                    String by = String.join(" ", tokens.subList(byIndex + 1, tokens.size()));
                    LocalDate byDateTime = LocalDate.parse(by);
                    command = new DeadlineCommand(taskName, byDateTime);
                    break;
                case EVENT:
                    int fromIndex = tokens.indexOf("/from");
                    int toIndex = tokens.indexOf("/to");
                    if (tokens.size() == 1 || fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex - 1 || toIndex == tokens.size() - 1) {
                        throw new InvalidParameterException(CommandEnum.EVENT);
                    }
                    taskName = String.join(" ", tokens.subList(1, fromIndex));
                    String from = String.join(" ", tokens.subList(fromIndex + 1, toIndex));
                    String to = String.join(" ", tokens.subList(toIndex + 1, tokens.size()));
                    LocalDate fromDateTime = LocalDate.parse(from);
                    LocalDate toDateTime = LocalDate.parse(to);
                    command = new EventCommand(taskName, fromDateTime, toDateTime);
                    break;
                case SCHEDULE:
                    if (tokens.size() == 1) {
                        throw new InvalidParameterException(CommandEnum.SCHEDULE);
                    }
                    tokens.remove(0);
                    try {
                        LocalDate date = LocalDate.parse(String.join(" ", tokens));
                        command = new ScheduleCommand(date);
                    } catch (DateTimeParseException e) {
                        throw new InvalidDateFormatException();
                    }
                    break;
                case ARCHIVE:
                    if (tokens.size() != 2) {
                        throw new InvalidParameterException(CommandEnum.ARCHIVE);
                    }
                    String fileName = tokens.get(1);
                    if (!fileName.toLowerCase().endsWith(".txt")) {
                        throw new InvalidParameterException(CommandEnum.ARCHIVE);
                    }
                    command = new ArchiveCommand(fileName);
                case FIND:
                    if (tokens.size() == 1) {
                        throw new InvalidParameterException(CommandEnum.SCHEDULE);
                    }
                    tokens.remove(0);
                    String keyword = String.join(" ", tokens);
                    command = new FindCommand(keyword);
                default:
            }
        }
        else {
            command = new Command(CommandEnum.UNKNOWN) {
                @Override
                public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException {
                    ui.printError();
                }
            };
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
                return new EventTask(parts[2], isDone, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
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

    /**
     * Validate task number.
     * Throws exception if task number is missing or task number is not in integer format.
     *
     * @param tokens  the parameters from user input
     * @param command the command type
     * @return the int
     * @throws InvalidParameterException if task number is missing or invalid
     */
    public static int validateTaskNumber(ArrayList<String> tokens, CommandEnum command) throws InvalidParameterException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens.get(1));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidParameterException(command);
        }
        return taskNumber;
    }

    /**
     * Validate single command, such as bye, list.
     * Throw exception if extra parameters are given.
     *
     * @param tokens  the parameters from user input
     * @param command the command type
     * @throws InvalidParameterException if extra parameters are given
     */
    public static void validateSingleCommand(ArrayList<String> tokens, CommandEnum command) throws InvalidParameterException {
        if (tokens.size() > 1) {
            throw new InvalidParameterException(command);
        }
    }
}
