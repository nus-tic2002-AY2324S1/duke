package task;

import command.AddTaskCommand;

import exceptions.MyCustomException;
import exceptions.InputBlankException;
import io.HelloAndByeMessage;
import io.TaskStorage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static command.UndoCommand.putInToStack;

import java.util.stream.Stream;

/**
 * This class is the main class of the program.
 * It will handle the input from the user.
 */
public class Craby extends HelloAndByeMessage {
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
        String nameOfList = checkListName(scanner);
        assert nameOfList != null;
        printFirstMessage(nameOfList);
        taskStorage = new TaskStorage(nameOfList.toLowerCase() + ".txt");
        List<Task> tasks = taskStorage.load();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();

            // the data store in the file slip by || so replace it to | to not make the data wrong
            input = input.replaceAll("\\|\\|", "|");

            boolean isExit = false;
            putInToStack(input, tasks); // use for undo command
            try {
                isExit = handleInput(input, tasks);
            } catch (InputBlankException e) {
                printInputBlankExceptionMessage();
            }
            if (isExit) {
                break;
            }
        }
    }

    private static String checkListName(Scanner scanner) {
        String listName = scanner.nextLine().trim();
        while (listName.isBlank()) {
            printEmptyTypeName();
            listName = scanner.nextLine();
        }
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(listName);
        while (matcher.find()) {
            printTypeNameError();
            listName = scanner.nextLine();
        }
        boolean isKeyword = isKeyword(listName);
        while (isKeyword) {
            printNameSameWithKeyWord();
            listName = scanner.nextLine();
            isKeyword = isKeyword(listName);
        }
        return listName;
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
        String checkInput;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputBlankException();
        }
        checkInput = checkInput.toUpperCase().trim();
        try {
            // check if the input is blank
            checkBlankDescription(checkInput);

            Keyword keyWords = getKeyword(checkInput);

            // check if the description is blank
            if (Arrays.asList(Keyword.ADD, Keyword.TODO, Keyword.DEADLINE, Keyword.EVENT).contains(keyWords)) {
                input = input.substring(checkInput.length());
            }
            if (input.isBlank()) {
                throw new InputBlankException();
            }
            // handle the input
            new CommandCreator().create(keyWords).handleCommand(input, tasks);
            boolean isExit = keyWords == Keyword.BYE || keyWords == Keyword.SWITCH;
            if (isExit) {
                return true;
            }
            taskStorage.save(tasks);

        } catch (MyCustomException e) {
            printInputBlankExceptionMessage();
            return false;
        }
        return false;
    }

    private static Keyword getKeyword(String checkInput) {
        try {
            return Keyword.valueOf(checkInput);
        } catch (IllegalArgumentException e) {
            // if the input is not a keyword
            // it will be a task
            return Keyword.DEFAULT;
        }
    }

    private static void checkBlankDescription(String checkInput) throws MyCustomException {
        boolean isBlank = checkInput.equals("/FROM") || checkInput.equals("/BY") || checkInput.equals("/TO");
        if (isBlank) {
            throw new MyCustomException();
        }
    }

}
