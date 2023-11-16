package nus.duke.ui;

import java.util.Scanner;

/**
 * The `Ui` class handles user interface interactions, including reading user commands and displaying messages.
 */
public class Ui {
    private static final String LINE_PREFIX = " ";
    private static final String LS = System.lineSeparator();

    private final Scanner stdinScanner = new Scanner(System.in);

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        return stdinScanner.nextLine();
    }

    /**
     * Displays a welcome message when DukeBot is launched.
     */
    public void showWelcome() {
        showLine();
        showWithPrefix(new String[] {
            " ______   __   __  ___   _  _______  _______  _______  _______ ",
            "|      | |  | |  ||   | | ||       ||  _    ||       ||       |",
            "|  _    ||  | |  ||   |_| ||    ___|| |_|   ||   _   ||_     _|",
            "| | |   ||  |_|  ||      _||   |___ |       ||  | |  |  |   |  ",
            "| |_|   ||       ||     |_ |    ___||  _   | |  |_|  |  |   |  ",
            "|       ||       ||    _  ||   |___ | |_|   ||       |  |   |  ",
            "|______| |_______||___| |_||_______||_______||_______|  |___|  ",
            "Hello! I'm DukeBot.",
            "What can I do for you?",
            "HINT: Use the 'help' command for more information."
        });
        showLine();
    }

    /**
     * Displays an error message if there was a problem loading tasks from storage.
     */
    public void showLoadingError() {
        showLine();
        showWithPrefix("Failed to load tasks from the storage file. An empty task list will be used instead.");
        showLine();
    }

    /**
     * Displays one or more messages to the user.
     *
     * @param messages The messages to be displayed.
     */
    public void showMessages(String... messages) {
        assert messages != null;

        showWithPrefix(messages);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        assert error != null;

        showWithPrefix(error);
    }

    /**
     * Displays a horizontal line as a visual separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    private void showWithPrefix(String... messages) {
        assert messages != null;

        for (String message : messages) {
            System.out.println(LINE_PREFIX + message.replaceAll("\\R", LS + LINE_PREFIX));
        }
    }
}
