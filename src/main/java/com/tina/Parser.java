package com.tina;

import com.tina.command.*;
import com.tina.exception.DukeException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidParameterException;
import com.tina.task.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static Command parseInputToCommand(String userInput) throws DukeException {
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
                    LocalDateTime byDateTime = LocalDateTime.parse(by);
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
                    LocalDateTime fromDateTime = LocalDateTime.parse(from);
                    LocalDateTime toDateTime = LocalDateTime.parse(to);
                    command = new EventCommand(taskName, fromDateTime, toDateTime);
                    break;
                default:
            }
        }
        else {
            command = new Command(CommandEnum.UNKNOWN) {
                @Override
                public void execute(TaskList taskList, Ui ui) throws DukeException {
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
     * @param line
     * @return
     */
    public static Task parseStorageToTask(String line) throws InvalidFileFormatException {
        String[] parts = line.split("\\s*\\|\\s*");
        boolean isDone = parts[1].equals("X");
        switch (parts[0]) {
            case "T":
                return new TodoTask(parts[2], isDone);
            case "D":
                return new DeadlineTask(parts[2], isDone, LocalDateTime.parse(parts[3]));
            case "E":
                return new EventTask(parts[2], isDone, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
            default:
                throw new InvalidFileFormatException();
        }
    }

    public static ArrayList<String> parseTasksToStorage(TaskList tasks) {
        ArrayList<String> taskArray = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            taskArray.add(task.toStorage());
        }
        return taskArray;
    }

    public static int validateTaskNumber(ArrayList<String> tokens, CommandEnum command) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens.get(1));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidParameterException(command);
        }
        return taskNumber;
    }

    public static void validateSingleCommand(ArrayList<String> tokens, CommandEnum command) throws DukeException {
        if (tokens.size() > 1) {
            throw new InvalidParameterException(command);
        }
    }
}
