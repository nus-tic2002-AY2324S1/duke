package io;

import task.Task;

import java.util.List;

/**
 * CrabyMessage class is a class that store all the messages that CrabY will use.
 * because the file quite long, I separate the help message into another file.
 * It has a method to handle the command.
 */
public class CrabyMessage {
    public static void printHello() {
        System.out.println(line + System.lineSeparator() +

                "   ____                  _      __   __" + System.lineSeparator() +
                "  / ___|  _ __    __ _  | |__   \\ \\ / /" + System.lineSeparator() +
                " | |     | '__|  / _` | | '_ \\   \\ V /" + System.lineSeparator() +
                " | |___  | |    | (_| | | |_) |   | |" + System.lineSeparator() +
                "  \\____| |_|     \\__,_| |_.__/    |_|" + System.lineSeparator() +
                System.lineSeparator() + line);

        System.out.println("   Hi Amber ♡, I'm CrabY 🦀"
                + System.lineSeparator() +
                "   How can I help you today? ˃ᴗ˂"
                + System.lineSeparator() + line);
    }

    public static final String line = "  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈";
    public static final String blank = "   ";

    public static void printByeMessage() {
        System.out.println(blank + "Bye Amber ♡, hope to see you again soon!");
        System.out.println(line);
        System.out.println(
                "               __   ♡   __" + System.lineSeparator() +
                        "              /           \\" + System.lineSeparator() +
                        "             (  / @   @ \\  )" + System.lineSeparator() +
                        "              \\(_ _\\_/_ _)/" + System.lineSeparator() +
                        "            (\\ `-/     \\-' /)" + System.lineSeparator() +
                        "             \"===\\     /===\"" + System.lineSeparator() +
                        "              .==')___(`==." + System.lineSeparator() +
                        "               .='     `=.");
    }

    public static void printInputBlankExceptionMessage() {
        System.out.println(blank + "Oops!!! The description cannot be empty.");
        System.out.println(blank + "╰┈➤Type \"help\" if you need to see the list of command" + System.lineSeparator() + line);
    }

    public static void printNumOutOfTask(int input) {
        System.out.print(blank + "Oops!!! Something wrong! Your list only have 1 ➞ ");
        System.out.println(input + " tasks.");
        System.out.println(blank + "Please try again with another number ☘");
        System.out.println(line);
    }

    public static void printAddMessage(String input, List<Task> tasks) {
        System.out.println(blank + "✎ added:");
        System.out.println(blank + "╰┈➤ " + input + " - to your list");
        printCountTask(tasks.size());
    }

    public static void printDateTimeParseExceptionMessage() {
        System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
        System.out.println(blank + "Please enter in this format after used /by or /from and /to");
        System.out.println(blank + "╰┈➤ Try with: \"dd/mm/yyyy hhmm\" e.g: 12/12/2020 1800");
        System.out.println(line);
    }

    //Print Delete all Message
    public static void printDeleteAllMessage() {
        System.out.println(blank + "✂ Ok, I clean up all your tasks");
        System.out.println(blank + "╰┈➤ Let's start a new checklist");
        System.out.println(line);
    }

    // Print DeleteCommand Message
    public static void printDeleteMessage(String input) {
        System.out.println(blank + "✂ Noted. I've removed this task:");
        System.out.println(blank + "╰┈➤ " + input + " - in your list");
    }

    public static void printCountTask(int input) {
        System.out.println(blank + "Now you have: " + input + " in your list \uD83D\uDDCE.");
        System.out.println(line);
    }

    public static void printDeleteErrorMessage() {
        System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
        System.out.println(blank + "╰┈➤ Try with: delete [integer] e.g: delete 1");
        System.out.println(blank + "          or: deleteall to delete all the tasks in your list.");
        System.out.println(line);
    }

    //Print ListCommand Message
    public static String printEndOfList = blank + " ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦";
    public static String printStartOfList = blank + " ╭─────────────────────────────────╮";

    public static void printEmptyList() {
        System.out.println(printStartOfList);
        System.out.println(blank + "    ░░░░ Your list is empty! ░░░░");
        System.out.println(printEndOfList);
        System.out.println(line);
    }

    public static void printListMessage(List<Task> tasks) {
        System.out.println(printStartOfList);
        System.out.println(blank + "  Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            if (i <= 9) {
                System.out.println("   " + i + ".  " + tasks.get(i - 1));
            } else if (i > 99) {
                System.out.println("   " + i + " " + tasks.get(i - 1));
            } else {
                System.out.println("   " + i + ". " + tasks.get(i - 1));
            }
        }
        System.out.println(printEndOfList);
        System.out.println(line);
    }

    //Print MarkCommand Message
    public static void printMarkMessage(String input) {
        System.out.println(blank + "Nice! I've marked this task as DONE ツ:");
        System.out.println(blank + "╰┈➤ " + input);
        System.out.println(line);
    }

    public static void printMarkNumFormatExceptionMessage() {
        System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
        System.out.println(blank + "╰┈➤ Try with: mark [integer] e.g: mark 1");
        System.out.println(line);
    }

    //Print UnmarkCommand Message
    public static void printUnmarkMessage(String input) {
        System.out.println(blank + "OK, I've marked this task as ☉⌓☉ NOT DONE yet:");
        System.out.println(blank + "╰┈➤ " + input);
        System.out.println(line);
    }

    public static void printUnmarkNumFormatExceptionMessage() {
        System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
        System.out.println(blank + "╰┈➤ Try with: unmark [integer] e.g: unmark 1");
        System.out.println(line);
    }

    //Print FindCommand Message
    public static void printFindMessage(List<String> listFound) {
        System.out.println(printStartOfList);
        System.out.println(blank + "⌖ Here are the matching tasks in your list:");
        for (String s : listFound) {
            System.out.println(blank + s);
        }
        System.out.println(printEndOfList);
        System.out.println(line);
    }

    public static void printNoMatchingTasks() {
        System.out.println(blank + "✘ No matching tasks in your list.");
        System.out.println(blank + "╰┈➤ Please try with another keyword ☘");
        System.out.println(line);
    }

    //Print SortByCommand Message
    public static void printSortByErrorMessage() {
        System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
        System.out.println(blank + "╰┈➤ Try with: sortby [type/date] e.g: sortby type");
        System.out.println(line);
    }

    public static void printSortByEmptyList() {
        System.out.println(blank + "Oops!!! your list are empty.");
        System.out.println(blank + "╰┈➤ Please add some tasks before use Sortby ☘");
        System.out.println(line);
    }

    //Print BlahCommand Message
    public static void printBlahMessage() {
        System.out.println(blank + "Oops!!! I'm sorry, but I don't know what that means ☘");
        System.out.println(line);
    }
}

