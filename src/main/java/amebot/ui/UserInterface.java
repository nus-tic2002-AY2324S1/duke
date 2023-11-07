package amebot.ui;

import amebot.common.Title;
import amebot.common.Messages;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * UserInterface class handles the user input command and
 * prints the welcome and output messages.
 */
public class UserInterface {
    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        Title.printHeader();
        Title.printGreeting();
    }

    /**
     * Returns the user input command.
     *
     * @return User input command.
     */
    public String getInputCommand() {
        Scanner scanInput = new Scanner(System.in);
        String commandLine = scanInput.nextLine().toLowerCase();
        return commandLine;
    }

    /**
     * Prints the output.
     *
     * @param logs List of logs to be printed.
     */
    public void printOutput(ArrayList<String> logs) {
        if (!logs.isEmpty()) {
            System.out.println(Messages.TOP_BORDER);

            for (String log : logs) {
                System.out.println(log);
            }

            System.out.println(Messages.BOTTOM_BORDER);
        }
    }
}
