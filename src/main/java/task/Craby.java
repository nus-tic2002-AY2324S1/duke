package task;

import command.*;
import exceptions.InputBlankException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Craby extends CrabyMessage {
    public static void crabySysterm() {
        System.out.println(line + "\n" + logo + line);
        System.out.println(hello + "\n" + line);
        Scanner scanner = new Scanner(System.in);
        String answerName = scanner.nextLine();
        System.out.print("   Hi " + answerName);
        System.out.println(" ♡, How can I help you today?" + "\n" + line);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;
            try {
                exit = handleInput(input, tasks, answerName);
            } catch (InputBlankException e) {
                System.out.println("   Oops!!! The description cannot be empty.");
                System.out.println("   Please try again!\n" + line);
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
        checkInput = checkInput.toLowerCase().trim();
        switch (checkInput) {
            case "list":
                handleListCommand(tasks);
                break;
            case "blah":
                handleBlahCommand();
                break;
            case "bye":
                exit = handleByeCommand(answerName);
                break;
            case "mark":
                handleMarkCommand(input, tasks);
                break;
            case "unmark":
                handleUnmarkCommand(input, tasks);
                break;
            default:
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
