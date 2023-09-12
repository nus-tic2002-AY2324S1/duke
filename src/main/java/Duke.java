import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String logo = "   ____                  _      __   __\n" +
            "  / ___|  _ __    __ _  | |__   \\ \\ / /\n" +
            " | |     | '__|  / _` | | '_ \\   \\ V /\n" +
            " | |___  | |    | (_| | | |_) |   | |\n" +
            "  \\____| |_|     \\__,_| |_.__/    |_|\n" +
            "\n";
    public static final String line = "  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈";
    public static final String hello = "   Hello! I'm CrabY 🦀\n" + "   What is your name? ˃ᴗ˂";

    public static void main(String[] args) {
        System.out.println(line + "\n" + logo + "\n" + hello + "\n" + line);
        Scanner scanner = new Scanner(System.in);
        String answerName = scanner.nextLine();
        System.out.println("   Hi " + answerName + " ♡, How can I help you today?" + "\n" + line);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            boolean exit = false;
            if (!input.isBlank()) {
                exit = handleInput(input, tasks, answerName);
            } else {
                System.out.println("   Oops!!! The description cannot be empty.\n Please try again!\n" + line);
            }
            if (exit) {
                break;
            }
        }
    }

    private static boolean handleInput(String input, List<Task> tasks, String answerName) {
        boolean exit = false;
        String checkInput = input;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("   Oops!!! The description cannot be empty.\n");
            System.out.println("   Please try again!\n" + line);
            return false;
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
