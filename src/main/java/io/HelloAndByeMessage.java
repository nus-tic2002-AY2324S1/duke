package io;

/**
 * HelloAndByeMessage class is a class that handle some message when Craby start.
 * It has a method to print out the hello and bye message.
 */
public class HelloAndByeMessage extends CrabyMessage {
    /**
     * Sends the hello message to the user.
     */
    public static void printHello() {
        System.out.println(LINE + System.lineSeparator() +

                                   "   ____                  _      __   __" + System.lineSeparator() +
                                   "  / ___|  _ __    __ _  | |__   \\ \\ / /" + System.lineSeparator() +
                                   " | |     | '__|  / _` | | '_ \\   \\ V /" + System.lineSeparator() +
                                   " | |___  | |    | (_| | | |_) |   | |" + System.lineSeparator() +
                                   "  \\____| |_|     \\__,_| |_.__/    |_|" + System.lineSeparator() +
                                   System.lineSeparator() + LINE);

        System.out.println(SPACES + "Hi ♡, CrabY 🦀 here!");
        System.out.println(SPACES + "Which checklist do you want to work on today? (｡◕‿◕｡)");
        System.out.println(SPACES + "╰┈➤You can typing: \"School\" | \"Work\" | \"Personal\" |");
        System.out.println(SPACES + "               or  [checklist-name] if you wanna creat a new list.");
        System.out.println(LINE);
    }

    /**
     * Sends the empty type name message to the user.
     */
    public static void printEmptyTypeName() {
        System.out.println(SPACES + "Oops!!! The [checklist-name] cannot be empty.");
        System.out.println(SPACES + "NOTE: if you use the same [checklist-name] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }

    /**
     * Sends the type name error message to the user.
     */
    public static void printTypeNameError() {
        System.out.println(SPACES + "Oops!!! The [checklist-name] cannot contain special character.");
        System.out.println(SPACES + "NOTE: if you use the same [checklist-name] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }

    /**
     * Sends the name same with keyword message to the user.
     */
    public static void printNameSameWithKeyWord() {
        System.out.println(SPACES + "Oops!!! Before you start, pls give me [checklist-name].");
        System.out.println(SPACES + "╰┈➤Your input cannot same with my keyword");
        System.out.println(SPACES + "NOTE: if you use the same [checklist-name] that used before");
        System.out.println(SPACES + "╰┈➤You can continue to use the previous list of tasks.");
        System.out.println(LINE);
    }

    /**
     * Sends the first message to the user.
     *
     * @param input the input from the user.
     */
    public static void printFirstMessage(String input) {
        checkListName = input;
        System.out.println(SPACES + "Ok I got it! Let start with your ➞ " + input + " list.");
        System.out.println(
                SPACES + "╰┈➤Type \"help\" if you need to see what I can do for you" + System.lineSeparator() + LINE);
    }

    /**
     * Sends the bye message to the user.
     */
    public static void printByeMessage() {
        System.out.println(SPACES + "Bye ♡, hope to see you again soon! •ᴗ•");
        System.out.println(LINE);
        System.out.println(        "               __   ♡   __" + System.lineSeparator() +
                                   "              /           \\" + System.lineSeparator() +
                                   "             (  / @   @ \\  )" + System.lineSeparator() +
                                   "              \\(_ _\\_/_ _)/" + System.lineSeparator() +
                                   "            (\\ `-/     \\-' /)" + System.lineSeparator() +
                                   "             \"===\\     /===\"" + System.lineSeparator() +
                                   "              .==')___(`==." + System.lineSeparator() +
                                   "               .='     `=.");
    }

}

