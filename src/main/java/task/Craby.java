package task;

import command.ByeCommand;
import command.ListCommand;
import command.AddTaskCommand;
import command.BlahCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.MarkCommand;
import command.SortCommand;
import command.SwitchCommand;
import command.UnmarkCommand;
import command.UndoCommand;

import exceptions.MyCustomException;
import exceptions.InputBlankException;
import io.CrabyMessage;
import io.TaskStorage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static command.UndoCommand.putInToStack;

import java.util.stream.*;

/**
 * This class is the main class of the program.
 * It will handle the input from the user.
 */
public class Craby extends CrabyMessage {
    private static TaskStorage taskStorage;
    private static final String REGEX = ".*[^a-zA-Z0-9\\s].*";

    /**
     * This method will print out the logo and the hello message.
     * It will also handle the input from the user.
     */
    public static void crabySystem(boolean is1stTime) {
        if (is1stTime) {
            printHello();
        }
        Scanner scanner = new Scanner(System.in);
        String nameOfList = checkName(scanner);
        assert nameOfList != null;
        printFirstMessage(nameOfList);
        taskStorage = new TaskStorage(nameOfList.toLowerCase() + ".txt");
        List<Task> tasks = taskStorage.load();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;
            putInToStack(input, tasks); // use for undo command
            try {
                exit = handleInput(input, tasks);
            } catch (InputBlankException e) {
                printInputBlankExceptionMessage();
            }
            if (exit) {
                break;
            }
        }
    }

    private static String checkName(Scanner scanner) {
        String name = scanner.nextLine().trim();
        while (name.isBlank()) {
            printEmptyTypeName();
            name = scanner.nextLine();
        }
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(name);
        while (matcher.find()) {
            printTypeNameError();
            name = scanner.nextLine();
        }
        boolean isKeyword = isKeyword(name);
        while (isKeyword) {
            printNameSameWithKeyWord();
            name = scanner.nextLine();
            isKeyword = isKeyword(name);
        }
        return name;
    }

    private static boolean isKeyword(String name) {
        return Stream.of("list", "blah", "bye", "mark", "unmark", "delete", "find", "sort", "help", "undo")
                .anyMatch(name.toLowerCase()::equalsIgnoreCase);
    }

    /**
     * This method will handle the input from the user.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return true if the user input bye.
     * @throws InputBlankException if the input is blank.
     */
    private static boolean handleInput(String input, List<Task> tasks) throws InputBlankException {
        if (input.isBlank()) {
            throw new InputBlankException();
        }
        boolean exit = false;
        String checkInput;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputBlankException();
        }
        checkInput = checkInput.toUpperCase().trim();
        if (isBlankDescription(checkInput, exit)) {
            return exit;
        }
        try {
            Keyword keyWords = Keyword.valueOf(checkInput);
            switch (keyWords) {
            case LIST:
                handleListCommand(input, tasks);
                break;
            case BLAH:
                handleBlahCommand(input, tasks);
                break;
            case BYE:
                handleByeCommand(input, tasks);
                exit = true;
                break;
            case MARK:
                handleMarkCommand(input, tasks);
                break;
            case UNMARK:
                handleUnmarkCommand(input, tasks);
                break;
            case DELETE:
                handleDeleteCommand(input, tasks);
                break;
            case FIND:
                handleFindCommand(input, tasks);
                break;
            case SORT:
                handleSortByCommand(input, tasks);
                break;
            case HELP:
                handleHelpCommand(input, tasks);
                break;
            case UNDO:
                handleUndoCommand(input, tasks);
                break;
            case SWITCH:
                handleSwitchCommand(input, tasks);
                exit = true;
                break;
            case ADD:
            case TODO:
            case DEADLINE:
            case EVENT:
            default:
                input = input.substring(checkInput.length());
                addTaskCommand(input, tasks);
                break;
            }
        } catch (IllegalArgumentException e) {
            addTaskCommand(input, tasks);
        }
        return exit;
    }


    private static boolean isBlankDescription(String checkInput, boolean exit) {
        try {
            if (checkInput.equals("/FROM") || checkInput.equals("/BY") || checkInput.equals("/TO")) {
                throw new MyCustomException();
            }
        } catch (MyCustomException e) {
            printInputBlankExceptionMessage();
            return true;
        }
        return false;
    }

    private static void handleUndoCommand(String input, List<Task> tasks) {
        new UndoCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleHelpCommand(String input, List<Task> tasks) {
        new HelpCommand().handleCommand(input, tasks);
    }

    private static void addTaskCommand(String input, List<Task> tasks) {
        new AddTaskCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleUnmarkCommand(String input, List<Task> tasks) {
        new UnmarkCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleMarkCommand(String input, List<Task> tasks) {
        new MarkCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleDeleteCommand(String input, List<Task> tasks) {
        new DeleteCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleBlahCommand(String input, List<Task> tasks) {
        new BlahCommand().handleCommand(input, tasks);
    }

    private static void handleListCommand(String input, List<Task> tasks) {
        new ListCommand().handleCommand(input, tasks);
    }

    private static void handleSortByCommand(String input, List<Task> tasks) {
        new SortCommand().handleCommand(input, tasks);
    }

    private static void handleFindCommand(String input, List<Task> tasks) {
        new FindCommand().handleCommand(input, tasks);
    }

    private static void handleByeCommand(String input, List<Task> tasks) {
        new ByeCommand().handleCommand(input, tasks);
    }

    private static void handleSwitchCommand(String input, List<Task> tasks) {
        new SwitchCommand().handleCommand(input, tasks);
    }
}
