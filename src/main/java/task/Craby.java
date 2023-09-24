package task;

import command.*;
import exceptions.InputBlankException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Craby extends CrabyMessage {
    public static void crabySysterm() {
        System.out.println(line + System.lineSeparator() + logo + line);
        System.out.println(hello + System.lineSeparator() + line);
        Scanner scanner = new Scanner(System.in);
        String answerName = scanner.nextLine();
        System.out.print("   Hi " + answerName);
        System.out.println(" ♡, How can I help you today?" + System.lineSeparator() + line);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;
            try {
                exit = handleInput(input, tasks, answerName);
            } catch (InputBlankException e) {
                System.out.println("   Oops!!! The description cannot be empty.");
                System.out.println("   Please try again!" + System.lineSeparator() + line);
            }
            if (exit) {
                break;
            }
        }
    }

    private static boolean handleInput(String input, List<Task> tasks, String answerName) throws InputBlankException {
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
                    handleListCommand(tasks);
                    break;
                case BLAH:
                    handleBlahCommand();
                    break;
                case BYE:
                    exit = handleByeCommand(answerName);
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
            }
        } catch (IllegalArgumentException ie) {
            addTaskCommand(input, tasks);
        }
        return exit;
    }

    private static void addTaskCommand(String input, List<Task> tasks) {
        new AddTaskCommand().addTaskCommand(input, tasks);
    }

    private static void handleUnmarkCommand(String input, List<Task> tasks) {
        new UnmarkCommand().handleUnmarkCommand(input, tasks);
    }

    private static void handleMarkCommand(String input, List<Task> tasks) {
        new MarkCommand().handleMarkCommand(input, tasks);
    }

    private static void handleDeleteCommand(String input, List<Task> tasks) {
        new DeleteCommand().handleDeleteCommand(input, tasks);
    }

    private static void handleBlahCommand() {
        new BlahCommand().handleBlahCommand();
    }

    private static void handleListCommand(List<Task> tasks) {
        new ListCommand().handleListCommand(tasks);
    }

    private static boolean handleByeCommand(String answerName) {
        new ByeCommand().handleByeCommand(answerName);
        return true;
    }
}
