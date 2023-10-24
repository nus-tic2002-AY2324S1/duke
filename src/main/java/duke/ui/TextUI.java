package duke.ui;

import static duke.common.Messages.MSG_GOODBYE;
import static duke.common.Messages.MSG_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUI class controls inputs and outputs of user
 */
public class TextUI {
    private static final String DIVIDER = "--------------------------------------------------";
    private final Scanner in;
    private final PrintStream out;


    public TextUI() {
        this (System.in, System.out);
    }

    /**
     * Creates a TextUI class that contains one input and one output variables
     *
     * @param in contains the input from the user
     * @param out contains the output for the user
     */
    public TextUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a welcome message
     */
    public void printWelcomeMsg() {
        printMsg(DIVIDER, MSG_WELCOME, DIVIDER);
    }

    /**
     * Prints a goodbye message
     */
    public void printGoodbyeMsg() {
        printMsg(DIVIDER, MSG_GOODBYE, DIVIDER);
    }

    /**
     * Prints a generic message
     *
     * @param message string input for message
     */
    public void printMessage(String message) {
        printMsg(DIVIDER, message, DIVIDER);
    }

    /**
     * Returns a string that contains the input of the user. If the input is blank, will loop
     *
     * @return string input from the user
     */
    public String getCommand() {
        out.print("Your instruction: ");
        String inputLine = in.nextLine();

        while (inputLine.isEmpty()) {
            inputLine = in.nextLine();
        }

        return inputLine;
    }

    /**
     * Prints into the screen all string arguments
     *
     * @param msg string of arguments
     */
    private void printMsg(String... msg) {
        for (String m : msg) {
            System.out.println(m);
        }
    }
}
