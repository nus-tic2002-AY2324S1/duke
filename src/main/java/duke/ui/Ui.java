package duke.ui;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    public static final String DIVIDER = "=".repeat(80);
    private static final String NEWLINE = System.lineSeparator();
    private final static int INDENT_RESPONSE = 5;
    private final static int INDENT_LINE = 4;
    private final static String HELLO_I_AM_LUNA = "Hello! I'm Luna";
    private final static String WHAT_CAN_DO_FOR_U = "What can I do for you?";
    private final static String LOGO =
            "      _                    \n"
                    + "     | |    _   _ _  __     __\n"
                    + "     | |   | | | | | __  \\/ __ \\\n"
                    + "     | |___| |_| | |   | | |__| |\n"
                    + "     |_____|\\__,_|_|   |_|_|  |_|\n";

    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    /**
     * Displays an error message related to loading data.
     *
     * @param message The error message to be display
     */
    public void showLoadingError(String message) {
        showResponseToUser(message);
    }

    /**
     * prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        displayMessage(
                generateSpaces(INDENT_LINE) + DIVIDER,
                logoString(),
                generateSpaces(INDENT_LINE) + DIVIDER,
                generateSpaces(INDENT_RESPONSE) + HELLO_I_AM_LUNA,
                generateSpaces(INDENT_RESPONSE) + WHAT_CAN_DO_FOR_U,
                generateSpaces(INDENT_LINE) + DIVIDER);
    }

    /**
     * Reads user command
     *
     * @return user command from the standard input.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * print divider line
     */
    public void showLine() {
        out.println(generateSpaces(INDENT_LINE) + DIVIDER);
    }

    /**
     * Returns the logo of the application as a String.
     *
     * @return A String representing the logo of the application.
     */
    private static String logoString() {
        return LOGO;
    }

    /**
     * Generates a String consisting of a specified number of empty spaces
     *
     * @param n The number of empty spaces to generate.
     * @return A String containing n empty spaces.
     */
    private static String generateSpaces(int n) {
        return (" ".repeat(n));
    }

    /**
     * Returns a prefix space as a string for chatbot responses.
     *
     * @return A String containing spaces for chatbot response formatting.
     */
    public static String getPrefixSpace() {
        return generateSpaces(INDENT_RESPONSE);
    }

    /**
     * Returns a prefix space as a string for chatbot print a line.
     *
     * @return A String containing spaces for chatbot print line formatting.
     */
    public static String getPrintLinePrefixSpace() {
        return generateSpaces(INDENT_LINE);
    }

    /**
     * Displays messages to the user.
     *
     * @param message Messages to be displayed to the user (Varargs)
     */
    public void displayMessage(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", NEWLINE));
        }
    }

    /**
     * Displays a list of messages to the user, framed by lines at the top and bottom.
     * Newlines in messages are replaced with the system-independent line separator.
     *
     * @param message List of messages to be displayed to the user.
     */
    public void showResponseToUser(ArrayList<String> message) {
        showLine();
        for (String m : message) {
            out.println(getPrefixSpace() + m.replace("\n", NEWLINE + getPrefixSpace()));
        }
        showLine();
    }

    /**
     * Displays a list of messages to the user, framed by lines at the top and bottom.
     * Newlines in messages are replaced with the system-independent line separator.
     *
     * @param message messages to be displayed to the user. (Varargs)
     */
    public void showResponseToUser(String... message) {
        showLine();
        for (String m : message) {
            out.println(getPrefixSpace() + m.replace("\n", NEWLINE + getPrefixSpace()));
        }
        showLine();
    }
}
