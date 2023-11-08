package io;

import java.io.*;
import task.Task;

import java.util.List;

/**
 * CrabyMessage class is a class that stores all the messages that CrabY will use.
 * Because the file is quite long, I separate the help, hello and bye messages into another file.
 * It has a method to handle the command.
 */
public class CrabyMessage {
    public static final String LINE = "  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈";
    public static final String SPACES = "   ";
    public static String checkListName;

    /**
     * Sends the blank input message to the user.
     */
    public static void printInputBlankExceptionMessage() {
        System.out.println(SPACES + "Oops!!! The description cannot be empty.");
        System.out.println(
                SPACES + "╰┈➤Type \"help\" if you need to see the list of command" + System.lineSeparator() + LINE);
    }

    /**
     * Sends the hello message to the user.
     *
     * @param input the input from the user.
     */
    public static void printEmptyListForAllCommand(String input) {
        String[] inputArr = input.split(" ");
        System.out.println(SPACES + "Oops!!! your list are empty (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Please add some tasks before use " + inputArr[0].trim() + " ☘");
        System.out.println(LINE);
    }

    /**
     * Sends the invalid number message to the user.
     *
     * @param input the input from the user.
     */
    public static void printNumOutOfTask(int input) {
        System.out.print(SPACES + "Oops!!! Something wrong! Your list only have 1 ➞ ");
        System.out.println(input + " tasks (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Please try again with another number ☘");
        System.out.println(LINE);
    }

    /**
     * Sends the task added message to the user.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     */
    public static void printAddMessage(String input, List<Task> tasks) {
        System.out.println(SPACES + "✎ added:");
        System.out.println(SPACES + "╰┈➤ " + input + " - to your list");
        printCountTask(tasks.size());
    }

    /**
     * Sends the task count message to the user.
     *
     * @param input the input from the user.
     */
    public static void printCountTask(int input) {
        System.out.println(SPACES + "Now you have: " + input + " tasks in your list \uD83D\uDDCE.");
        System.out.println(LINE);
    }

    /**
     * Sends the wrong date format message to the user.
     */
    public static void printDateTimeParseExceptionMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format (╯︵╰,)");
        System.out.println(SPACES + "Please enter in this format after used /by or /from and /to");
        System.out.println(SPACES + "╰┈➤ Try with: \"/by dd/mm/yyyy hhmm\" e.g: /by 12/12/2020 1800");
        System.out.println(SPACES + "          or: \"/by mon\" - CrabY will put the date to the next Monday");
        System.out.println(LINE);
    }

    /**
     * Sends the delete-all message to the user.
     */
    public static void printDeleteAllMessage() {
        System.out.println(SPACES + "✂ Ok, I clean up all your tasks");
        System.out.println(SPACES + "╰┈➤ Let's start a new checklist");
        System.out.println(LINE);
    }

    /**
     * Sends the deleted message to the user.
     */
    public static void printDeleteMessage(String input) {
        System.out.println(SPACES + "✂ Noted. I've removed this task:");
        System.out.println(SPACES + "╰┈➤ " + input + " - in your list");
    }

    /**
     * Sends the delete error message to the user.
     */
    public static void printDeleteErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Try with: delete [integer] e.g: delete 1");
        System.out.println(SPACES + "          or: delete all to delete all the tasks in your list.");
        System.out.println(LINE);
    }

    private static final String PRINT_END_OF_LIST = SPACES + "꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦";
    private static final String PRINT_START_OF_LIST = SPACES + "╭─────────────────────────────────────╮";

    /**
     * Sends the list an empty message to the user.
     */
    public static void printEmptyList() {
        System.out.println(PRINT_START_OF_LIST);
        System.out.println(SPACES + " ░░░░ Your " + checkListName.toUpperCase() + " list is empty! ░░░░");
        System.out.println(SPACES + "      Let's start a new checklist");
        System.out.println(PRINT_END_OF_LIST);
        System.out.println(LINE);
    }

    /**
     * Sends the list tasks message to the user.
     *
     * @param tasks the list of tasks.
     */
    public static void printListMessage(List<Task> tasks) {
        System.out.println(PRINT_START_OF_LIST);
        System.out.println(SPACES + "Here are the tasks in your " + checkListName.toUpperCase() + " list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            if (i <= 9) {
                System.out.println("   " + i + ".  " + tasks.get(i - 1));
            } else if (i > 99) {
                System.out.println("   " + i + " " + tasks.get(i - 1));
            } else {
                System.out.println("   " + i + ". " + tasks.get(i - 1));
            }
        }
        System.out.println(PRINT_END_OF_LIST);
        System.out.println(LINE);
    }

    /**
     * Sends the mark message to the user.
     *
     * @param input the input from the user.
     */
    public static void printMarkMessage(String input) {
        System.out.println(SPACES + "Nice! I've marked this task as DONE ツ:");
        System.out.println(SPACES + "╰┈➤ " + input);
        System.out.println(LINE);
    }

    /**
     * Sends the mark-all messages to the user.
     */
    public static void printMarkAllMessage() {
        System.out.println(SPACES + "Congratulations! You have COMPLETED all your tasks.");
        System.out.println(LINE);
    }

    /**
     * Sends the mark-error message to the user.
     */
    public static void printMarkErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Try with: mark [integer] e.g: mark 1");
        System.out.println(SPACES + "          or: mark all - to mark all the tasks in your list.");
        System.out.println(LINE);
    }

    /**
     * Sends the unmark message to the user.
     *
     * @param input the input from the user.
     */
    public static void printUnmarkMessage(String input) {
        System.out.println(SPACES + "OK, I've marked this task as ☉⌓☉ NOT DONE yet:");
        System.out.println(SPACES + "╰┈➤ " + input);
        System.out.println(LINE);
    }

    /**
     * Sends the unmark-all message to the user.
     */
    public static void printUnmarkAllMessage() {
        System.out.println(SPACES + "OK, I've marked ALL tasks as ☉⌓☉ NOT DONE yet:");
        System.out.println(LINE);
    }

    /**
     * Sends the unmark-error message to the user.
     */
    public static void printUnmarkErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Try with: unmark [integer] or e.g: unmark 1");
        System.out.println(SPACES + "          or: unmark all - to unmark all the tasks in your list.");
        System.out.println(LINE);
    }

    /**
     * Sends the find error message to the user.
     *
     * @param listFound the list of tasks that match with the keyword.
     * @param keyword   the keyword that user wants to find.
     */
    public static void printFindMessage(List<String> listFound, String keyword) {
        System.out.println(SPACES + "⌖ Here are the list tasks matching with \"" + keyword + "\":");
        for (String s : listFound) {
            System.out.println(SPACES + s);
        }
        System.out.println(LINE);
    }

    /**
     * Sends no match message to the user.
     *
     * @param keyword the keyword that user wants to find.
     */
    public static void printNoMatchingTasks(String keyword) {
        System.out.println(SPACES + "✘ No tasks matching with \"" + keyword + "\" in your list.");
        System.out.println(SPACES + "╰┈➤ Please try with another keyword ☘");
        System.out.println(LINE);
    }

    /**
     * Sends the sort message to the user.
     */
    public static void printSortErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format (╯︵╰,)");
        System.out.println(SPACES + "╰┈➤ Try with: sort [type/date] e.g: sort type");
        System.out.println(LINE);
    }

    /**
     * Sends the blah message to the user.
     */
    public static void printBlahMessage() {
        System.out.println(SPACES + "Oops!!! I'm sorry, but I don't know what that means ☘");
        System.out.println(LINE);
    }

    /**
     * Sends the undo message to the user.
     *
     * @param command the command that user wants to undo.
     */
    public static void printUndoMessage(String command) {
        String customizeMessage = "add - ";
        boolean isCustomize = command.contains("delete") || command.contains("mark") || command.contains("sort");
        if (isCustomize) {
            customizeMessage = " ";
        }
        System.out.println(SPACES + "Undo Successful!!!");
        System.out.println(SPACES + "╰┈➤I've undo your command:" + customizeMessage + command);
        System.out.println(LINE);
    }

    /**
     * Sends the undo error message to the user.
     */
    public static void printUndoError() {
        System.out.println(SPACES + "Oops!!! Undo unsuccessful. I already undo all the input you just entered.");
        System.out.println(SPACES + "╰┈➤Please try again with another command ☘");
        System.out.println(LINE);
    }

    /**
     * Sends the switch message to the user.
     */
    public static void printSwitchMessage() {
        System.out.println(SPACES + "Which checklist do you want to switch to? (｡◕‿◕｡)");
        System.out.println(SPACES + "╰┈➤You can typing: \"School\" | \"Work\" | \"Personal\" |");
        System.out.println(SPACES + "               or  [checklist-name] if you want to creat a new list.");
        System.out.println(LINE);
    }

    /**
     * Sends the checklist message to the user.
     */
    public static void printChecklistMessage(File[] files) {
        System.out.println(PRINT_START_OF_LIST);
        if (files != null) {
            System.out.println(SPACES + "Here are categories of your checklist:");
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    int indexOfLastDot = fileName.lastIndexOf(".");
                    if (indexOfLastDot > 0) {
                        fileName = fileName.substring(0, indexOfLastDot).toUpperCase();
                    }
                    System.out.println(SPACES + "……✎ " + fileName + " \uD83D\uDDCE.");
                }
            }
        } else {
            System.out.println(SPACES + "You don't have any checklist yet.");
        }
        System.out.println(PRINT_END_OF_LIST);
        System.out.println(LINE);
    }
}

