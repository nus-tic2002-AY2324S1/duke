package amebot.tasks.commands;

import java.util.ArrayList;

import amebot.exceptions.InvalidInputException;
import amebot.App;
import amebot.tasks.Task;

public class Command {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static final String FROM_REGEX = " /from ";
    protected static final String TO_REGEX = " /to ";
    protected static final String DUE_REGEX = " /due ";
    protected static final String NAN = "NAN";

    public static void render(String input) throws InvalidInputException {
        String[] inputTexts = input.trim().split(" ");

        String command = extractCommand(inputTexts);
        String[] content = new String[2];
        int index = -1;

        switch (command) {
            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                content = extractContent(input);
                String desc = content[0];
                String date = content[1];
                AddTask.add(desc, date);
                break;
            case "list":
                printOutput();
                break;
            case "check":
                // Fallthrough
            case "uncheck":
                index = extractIndex(inputTexts);
                printOutput(index - 1, command);
                break;
            case "bye":
                App.exit();
                break;
            default:
                throw new InvalidInputException("Invalid Input Exception");
        }
    }

    public static String extractCommand(String[] inputTexts) {
        String command = "";

        try {
            command = inputTexts[0];
            checkCommand(command);
        } catch (InvalidInputException | NumberFormatException err) {
            System.out.println("You've entered an invalid command, please try again!");
        }

        return command;
    }

    public static void checkCommand(String command) throws InvalidInputException {
        boolean isValid = false;

        switch (command) {
            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "list":
                // Fallthrough
            case "check":
                // Fallthrough
            case "uncheck":
                // Fallthrough
            case "bye":
                isValid = true;
                break;
        }

        if (!isValid) {
            throw new InvalidInputException("Invalid Command Exception");
        }
    }

    public static String[] extractContent(String input) {
        int size = input.length();
        String[] content = new String[2];
        String desc = "";
        String date = "";
        int index = -1;

        try {
            if (input.contains("event")) {
                index = input.indexOf(FROM_REGEX);
                desc = input.substring(6, index);
                date = input.substring(index, size);
            } else if (input.contains("deadline")) {
                index = input.indexOf(DUE_REGEX);
                desc = input.substring(9, index);
                date = input.substring(index, size);
            } else {
                desc = input.substring(5);
                date = NAN;
            }

            content[0] = desc;
            content[1] = date;
            checkDate(date);
        } catch (InvalidInputException | NumberFormatException | StringIndexOutOfBoundsException |
                 NullPointerException err) {
            System.out.println("You've entered an invalid description, please try again!");
        }

        return content;
    }

    public static void checkDate(String date) throws InvalidInputException {
        boolean isValid = (date.contains(FROM_REGEX) && date.contains(TO_REGEX)) || date.contains(DUE_REGEX) || date.contains(NAN);

        if (!isValid) {
            throw new InvalidInputException("Invalid Description Exception");
        }
    }

    public static int extractIndex(String[] inputTexts) {
        int index = -1;

        try {
            index = Integer.parseInt(inputTexts[1]);
            checkIndex(index);
        } catch (InvalidInputException | NumberFormatException | IndexOutOfBoundsException err) {
            System.out.println("You've entered an invalid index, please try again!");
        }

        return index;
    }

    public static void checkIndex(int index) throws InvalidInputException {
        if (index < 0 && index > Task.getTaskListSize() - 1) {
            throw new InvalidInputException("Invalid Index Exception");
        }
    }

    public static void printOutput(Task item) {
        System.out.println(App.TOP_BORDER);

        item.printTask();
        System.out.println("Successfully added to list!\n"
                + "You've " + Task.getTaskListSize() + " item(s) in list~");

        System.out.println(App.BOTTOM_BORDER + "\n");
    }

    public static void printOutput() {
        System.out.println(App.TOP_BORDER);

        ListTask.list();

        System.out.println(App.BOTTOM_BORDER + "\n");
    }

    public static void printOutput(int index, String status) {
        System.out.println(App.TOP_BORDER);

        String message = "";
        message = tasks.get(index).setTaskStatus(status);
        System.out.println(message);
        tasks.get(index).printTask();

        System.out.println(App.BOTTOM_BORDER + "\n");
    }
}
