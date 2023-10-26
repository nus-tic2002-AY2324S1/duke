package io;

import task.Task;

import java.util.List;

/**
 * CrabyMessage class is a class that store all the messages that CrabY will use.
 * because the file quite long, I separate the help message into another file.
 * It has a method to handle the command.
 */
public class CrabyMessage {
    public static final String LINE = "  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈";
    public static final String SPACES = "   ";
    public static void printHello() {
        System.out.println(LINE + System.lineSeparator() +

                "   ____                  _      __   __" + System.lineSeparator() +
                "  / ___|  _ __    __ _  | |__   \\ \\ / /" + System.lineSeparator() +
                " | |     | '__|  / _` | | '_ \\   \\ V /" + System.lineSeparator() +
                " | |___  | |    | (_| | | |_) |   | |" + System.lineSeparator() +
                "  \\____| |_|     \\__,_| |_.__/    |_|" + System.lineSeparator() +
                System.lineSeparator() + LINE);

        System.out.println("   Hi Amber ♡, CrabY 🦀 here!"
                + System.lineSeparator() +
                "   What type of thing you wanna do right now? ˃ᴗ˂");
        System.out.println(LINE);
        System.out.println(SPACES + "╰┈➤Typing: \"School\" | \"Work\" | \"Personal\" | [name-of-list] if you wanna creat a new type.");
        System.out.println(LINE);
    }
    public static void printEmptyTypeName() {
        System.out.println(SPACES + "Oops!!! The [name-of-list] cannot be empty.");
        System.out.println(SPACES + "NOTE: if you use the same [name-of-list] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }
    public static void printTypeNameError() {
        System.out.println(SPACES + "Oops!!! The [name-of-list] cannot contain special character.");
        System.out.println(SPACES + "NOTE: if you use the same [name-of-list] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }
    public static void printNameSameWithKeyWord() {
        System.out.println(SPACES + "Oops!!! Before you start, pls give me [name-of-list]. (your input cannot same with my keyword)");
        System.out.println(SPACES + "NOTE: if you use the same [name-of-list] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }
    public static void printFirstMessage(String input){
        System.out.println(SPACES + "Ok I got it! Let start with your ➞ " + input + " list.");
        System.out.println(SPACES + "╰┈➤Type \"help\" if you need to see what I can do for you" + System.lineSeparator() + LINE);

    }
    public static void printByeMessage() {
        System.out.println(SPACES + "Bye Amber ♡, hope to see you again soon!");
        System.out.println(LINE);
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
        System.out.println(SPACES + "Oops!!! The description cannot be empty.");
        System.out.println(SPACES + "╰┈➤Type \"help\" if you need to see the list of command" + System.lineSeparator() + LINE);
    }
    public static void printEmptyListForAllCommand(String input) {
        String[] inputArr = input.split(" ");
        System.out.println(SPACES + "Oops!!! your list are empty.");
        System.out.println(SPACES + "╰┈➤ Please add some tasks before use " + inputArr[0].trim() + " ☘");
        System.out.println(LINE);
    }
    public static void printNumOutOfTask(int input) {
        System.out.print(SPACES + "Oops!!! Something wrong! Your list only have 1 ➞ ");
        System.out.println(input + " tasks.");
        System.out.println(SPACES + "Please try again with another number ☘");
        System.out.println(LINE);
    }
    public static void printAddMessage(String input, List<Task> tasks) {
        System.out.println(SPACES + "✎ added:");
        System.out.println(SPACES + "╰┈➤ " + input + " - to your list");
        printCountTask(tasks.size());
    }

    public static void printCountTask(int input) {
        System.out.println(SPACES + "Now you have: " + input + " tasks in your list \uD83D\uDDCE.");
        System.out.println(LINE);
    }

    public static void printDateTimeParseExceptionMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format.");
        System.out.println(SPACES + "Please enter in this format after used /by or /from and /to");
        System.out.println(SPACES + "╰┈➤ Try with: \"/by dd/mm/yyyy hhmm\" e.g: /by 12/12/2020 1800");
        System.out.println(SPACES + "          or: \"/by mon\" - CrabY will put the date to the next Monday");
        System.out.println(LINE);
    }
    //Print Delete all Message
    public static void printDeleteAllMessage() {
        System.out.println(SPACES + "✂ Ok, I clean up all your tasks");
        System.out.println(SPACES + "╰┈➤ Let's start a new checklist");
        System.out.println(LINE);
    }
    // Print DeleteCommand Message
    public static void printDeleteMessage(String input) {
        System.out.println(SPACES + "✂ Noted. I've removed this task:");
        System.out.println(SPACES + "╰┈➤ " + input + " - in your list");
    }
    public static void printDeleteErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format.");
        System.out.println(SPACES + "╰┈➤ Try with: delete [integer] e.g: delete 1");
        System.out.println(SPACES + "          or: deleteall to delete all the tasks in your list.");
        System.out.println(LINE);
    }
    //Print ListCommand Message
    public static final String PRINT_END_OF_LIST = SPACES + " ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦";
    public static final String PRINT_START_OF_LIST = SPACES + " ╭─────────────────────────────────╮";

    public static void printEmptyList() {
        System.out.println(PRINT_START_OF_LIST);
        System.out.println(SPACES + "    ░░░░ Your list is empty! ░░░░");
        System.out.println(SPACES + "     Let's start a new checklist");
        System.out.println(PRINT_END_OF_LIST);
        System.out.println(LINE);
    }
    public static void printListMessage(List<Task> tasks) {
        System.out.println(PRINT_START_OF_LIST);
        System.out.println(SPACES + "  Here are the tasks in your list:");
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
    //Print MarkCommand Message
    public static void printMarkMessage(String input) {
        System.out.println(SPACES + "Nice! I've marked this task as DONE ツ:");
        System.out.println(SPACES + "╰┈➤ " + input);
        System.out.println(LINE);
    }
    public static void printMarkNumFormatExceptionMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format.");
        System.out.println(SPACES + "╰┈➤ Try with: mark [integer] e.g: mark 1");
        System.out.println(LINE);
    }
    //Print UnmarkCommand Message
    public static void printUnmarkMessage(String input) {
        System.out.println(SPACES + "OK, I've marked this task as ☉⌓☉ NOT DONE yet:");
        System.out.println(SPACES + "╰┈➤ " + input);
        System.out.println(LINE);
    }
    public static void printUnmarkNumFormatExceptionMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format.");
        System.out.println(SPACES + "╰┈➤ Try with: unmark [integer] e.g: unmark 1");
        System.out.println(LINE);
    }
    //Print FindCommand Message
    public static void printFindMessage(List<String> listFound, String keyword) {
        System.out.println(SPACES + "⌖ Here are the list task matching with \"" + keyword + "\":");
        for (String s : listFound) {
            System.out.println(SPACES + s);
        }
        System.out.println(LINE);
    }
    public static void printNoMatchingTasks() {
        System.out.println(SPACES + "✘ No matching tasks in your list.");
        System.out.println(SPACES + "╰┈➤ Please try with another keyword ☘");
        System.out.println(LINE);
    }
    //Print SortByCommand Message
    public static void printSortErrorMessage() {
        System.out.println(SPACES + "Oops!!! Looks like you used the wrong format.");
        System.out.println(SPACES + "╰┈➤ Try with: sort [type/typerevert/date] e.g: sort type");
        System.out.println(LINE);
    }
    //Print BlahCommand Message
    public static void printBlahMessage() {
        System.out.println(SPACES + "Oops!!! I'm sorry, but I don't know what that means ☘");
        System.out.println(LINE);
    }
    //Print UndoCommand Message
    public static void printUndoMessage(String command, String customizeMessage) {
        System.out.println(SPACES + "Undo Successful!!!");
        System.out.println(SPACES + "╰┈➤I've undo your command:" + customizeMessage + command);
        System.out.println(LINE);
    }
    public static void printUndoError() {
        System.out.println(SPACES + "Oops!!! Undo unsuccessful. I already undo all the input you just entered.");
        System.out.println(SPACES + "╰┈➤Please try again with another command ☘");
        System.out.println(LINE);
    }
}

