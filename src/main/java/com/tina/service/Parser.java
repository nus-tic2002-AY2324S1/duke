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
 * The type Parser.
 */
public class Parser {

    /**
     * Parse input to command command.
     *
     * @param userInput the user input
     * @return the command
     * @throws InvalidParameterException  the invalid parameter exception
     * @throws InvalidDateFormatException the invalid date format exception
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
                default:
            }
        }
        else {
            command = new Command(CommandEnum.UNKNOWN) {
                @Override
                public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
                    ui.printError();
                }
            };
        }

        return command;
    }

    /**
     * Storage file format:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * T | 1 | join sports club
     *
     * @param line the line
     * @return task
     * @throws InvalidFileFormatException the invalid file format exception
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
     * Parse tasks to storage array list.
     *
     * @param tasks the tasks
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
     * Validate task number int.
     *
     * @param tokens  the tokens
     * @param command the command
     * @return the int
     * @throws InvalidParameterException the invalid parameter exception
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
     * Validate single command.
     *
     * @param tokens  the tokens
     * @param command the command
     * @throws InvalidParameterException the invalid parameter exception
     */
    public static void validateSingleCommand(ArrayList<String> tokens, CommandEnum command) throws InvalidParameterException {
        if (tokens.size() > 1) {
            throw new InvalidParameterException(command);
        }
    }
}
