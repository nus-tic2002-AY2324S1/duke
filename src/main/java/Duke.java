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
    public static final String crab = "              __       __\n" +
            "             /           \\\n" +
            "            (  / @   @ \\  )\n" +
            "             \\(_ _\\_/_ _)/\n" +
            "           (\\ `-/     \\-' /)\n" +
            "            \"===\\     /===\"\n" +
            "             .==')___(`==.\n" +
            "              .='     `=.";
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
            input = input.trim()
                    .replace("Deadline", "")
                    .replace("Todo", "")
                    .replace("Event", "")
                    .replace("deadline", "")
                    .replace("todo", "")
                    .replace("event", "");
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
        System.out.println(line + "\n" + crab);
    }

    private static boolean handleInput(String input, List<Task> tasks, String answerName) {
        boolean exit = false;
        if (input.contains("/by")) {
            String[] formatDeadline = input.split("/by");
            if (formatDeadline.length > 1) {
                tasks.add(new Deadline(formatDeadline[0].trim(), formatDeadline[1]));
                System.out.println("   ✎ added: " + input + " - to your list.");
                System.out.println("   You have " + tasks.size() + " tasks in the list \uD83D\uDDCE.\n" + line);
            } else {
                System.out.println("   Oops!!! Looks like you used the wrong format.\n   Please give more information after use /by\n" + line);
            }
        }
        if (input.contains("/from")) {
            String[] formatEvent = input.split("/from");
            if (formatEvent.length > 1) {
                if (formatEvent[1].contains(("/to"))) {
                    String[] timeEvent = formatEvent[1].split("/to");
                    tasks.add(new Event(formatEvent[0].trim(), timeEvent[0], timeEvent[1]));
                } else {
                    tasks.add(new Event(formatEvent[0].trim(), formatEvent[1]));
                }
                System.out.println("   ✎ added: " + input + " - to your list.");
                System.out.println("   You have " + tasks.size() + " tasks in the list \uD83D\uDDCE.\n" + line);
            } else {
                System.out.println("   Oops!!! Looks like you used the wrong format.\n   Please give more information after use /from\n" + line);
            }
        }


        String checkInput = input;
        try {
            checkInput = input.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("   Oops!!! The description cannot be empty.\n" + "   Please try again!\n" + line);
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
                handleDefaultCommand(input, tasks);

        }
        return exit;
    }

    private static void handleDefaultCommand(String input, List<Task> tasks) {
        if (!input.contains("/by") && !input.contains("/from")) {
            tasks.add(new Todo(input));
            System.out.println("   ✎ added: " + input + " - to your list.");
            System.out.println("   You have " + tasks.size() + " tasks in the list \uD83D\uDDCE.\n" + line);
        }
    }

    private static void handleUnmarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp+1).trim();
            int b = (Integer.parseInt(checkMark)) - 1;
            if (b >= tasks.size() || b < 0) {
                System.out.println("   Oops, something wrong! Your list only have 1 to " + tasks.size() + "tasks. \n" + "   Please try again!\n" + line);
                return;
            }
            tasks.get(b).setDone(false);
            System.out.println("   OK, I've marked this task as ☉⌓☉ NOT DONE yet:" + "\n" + "   ╰┈➤ " + tasks.get(b) + "\n" + line);
            return;
        } catch (NumberFormatException e) {
            System.out.println("   '" + input.split(" ")[1] + "' is invalid number. Please try again!\n" + line);
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("   Oops!!! Looks like you used the wrong format.\n   Try format: unmark [integer] e.g: unmark 1\n" + line);
            return;
        }
    }

    private static void handleMarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp+1).trim();
            int a = (Integer.parseInt(checkMark)) - 1;
            if (a >= tasks.size() || a < 0) {
                System.out.println("   Oops, something wrong! Your list only have 1 to " + tasks.size() + " tasks.\n" + "   Please try again!\n" + line);
                return;
            }
            tasks.get(a).setDone(true);
            System.out.println("   Nice! I've marked this task as DONE ツ:" + "\n" + "   ╰┈➤ " + tasks.get(a) + "\n" + line);
            return;
        } catch (NumberFormatException e) {
            System.out.println("   '" + input.split(" ")[1] + "' is invalid number. Please try again!\n" + line);
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("   Oops!!! Looks like you used the wrong format.\n   Try format: mark [integer] e.g: mark 1\n" + line);
            return;
        }
    }

    private static boolean handleByeCommand(String answerName) {
        boolean exit;
        System.out.println("   Bye " + answerName + " ♡, hope to see you again soon!");
        exit = true;
        return exit;
    }

    private static void handleBlahCommand() {
        System.out.println("   Oops!!! I'm sorry, but I don't know what that means ☘\n" + line);
    }

    private static void handleListCommand(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("   Your list is empty!\n" + line);
            return;
        } else {
            int i = 0;
            System.out.println("    ╭─────────────────────────────────╮");
            System.out.println("     Here are the tasks in your list:");
            for (Task s : tasks) {
                i++;
                System.out.println("   " + i + ". " + s);
            }
            System.out.println("    ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦");
            System.out.println(line);
            return;
        }
    }
}
