package task;

import command.*;
import exceptions.InputBlankException;
import io.CrabyMessage;
import io.TaskStorage;

import java.util.*;

/**
 * This class is the main class of the program.
 * It will handle the input from the user.
 */
public class Craby extends CrabyMessage {
    public static Stack<List<Task>> stackTaskList = new Stack<>();
    public static Stack<String> stackInput = new Stack<>();
    private static final TaskStorage taskStorage = new TaskStorage("./data/craby.txt");

    /**
     * This method will print out the logo and the hello message.
     * It will also handle the input from the user.
     */
    public static void crabySysterm() {
        printHello();
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = taskStorage.load();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;

            if(!isPutInStack(input)){
                List<Task> cloneTasks = new ArrayList<>();
                for (Task task : tasks) {
                    cloneTasks.add(task.clone());
                }
                stackTaskList.push(cloneTasks);
                stackInput.push(input);
            }
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
    protected static boolean isPutInStack(String input){
        String[] inputArr = input.split(" ");
        input = inputArr[0].trim().toLowerCase();
        return input.contains("list") || input.contains("find")
                || input.contains("help") || input.contains("blah")
                || input.contains("undo");
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
        if (input.isBlank()) throw new InputBlankException();
        boolean exit = false;
        String checkInput;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputBlankException();
        }
        checkInput = checkInput.toUpperCase().trim();
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
                    exit = handleByeCommand(input, tasks);
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
                case DELETEALL:
                    handleDeleteAllCommand(input, tasks);
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
                case TODO:
                case DEADLINE:
                case EVENT:
                    input = input.substring(checkInput.length());
                    addTaskCommand(input, tasks);
                    break;
            }
        } catch (IllegalArgumentException ie) {
            addTaskCommand(input, tasks);
        }
        return exit;
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

    private static void handleDeleteAllCommand(String input, List<Task> tasks) {
        new DeleteAllCommand().handleCommand(input, tasks);
        taskStorage.save(tasks);
    }

    private static void handleBlahCommand(String input, List<Task> tasks) {
        new BlahCommand().handleCommand(input, tasks);
    }

    private static void handleListCommand(String input, List<Task> tasks) {
        new ListCommand().handleCommand(input, tasks);
    }

    private static void handleSortByCommand(String input, List<Task> tasks) {
        new SortByCommand().handleCommand(input, tasks);
    }

    private static void handleFindCommand(String input, List<Task> tasks) {
        new FindCommand().handleCommand(input, tasks);
    }

    private static boolean handleByeCommand(String input, List<Task> tasks) {
        new ByeCommand().handleCommand(input, tasks);
        return true;
    }
}
