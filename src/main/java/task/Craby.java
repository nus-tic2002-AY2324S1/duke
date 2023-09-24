package task;

import command.*;
import exceptions.InputBlankException;
import io.TaskStorage;

import java.util.List;
import java.util.Scanner;

public class Craby extends CrabyMessage {
    private static TaskStorage taskStorage = new TaskStorage("./data/craby.txt");
    public static void crabySysterm() {
        System.out.println(line + System.lineSeparator() + logo + line);
        System.out.println(hello + System.lineSeparator() + line);
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = taskStorage.load();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;
            try {
                exit = handleInput(input, tasks);
            } catch (InputBlankException e) {
                System.out.println("   Oops!!! The description cannot be empty.");
                System.out.println("   Please try again!" + System.lineSeparator() + line);
            }
            if (exit) {
                break;
            }
        }
    }

    private static boolean handleInput(String input, List<Task> tasks) throws InputBlankException {
        if (input.isBlank()) throw new InputBlankException();
        boolean exit = false;
        String checkInput;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException ai) {
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
            }
        } catch (IllegalArgumentException ie) {
            addTaskCommand(input, tasks);
        }
        return exit;
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

    private static boolean handleByeCommand(String input, List<Task> tasks) {
        new ByeCommand().handleCommand(input, tasks);
        return true;
    }
}
