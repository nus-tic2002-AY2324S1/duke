package ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE_PREFIX = " ";
    private static final String LS = System.lineSeparator();

    private final Scanner stdinScanner = new Scanner(System.in);

    public String readCommand() {
        return stdinScanner.nextLine();
    }

    public void showWelcome() {
        showLine();
        showWithPrefix(new String[]{
                "Hello! I'm DukeBot",
                "What can I do for you?"
        });
        showLine();
    }

    public void showLoadingError() {
        showLine();
        showWithPrefix("Failed to load tasks from the storage file. An empty task list will be used instead.");
        showLine();
    }

    public void showMessages(String... messages) {
        showWithPrefix(messages);
    }


    public void showError(String error) {
        showWithPrefix(error);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    private void showWithPrefix(String... messages) {
        for (String message : messages) {
            System.out.println(LINE_PREFIX + message.replaceAll("\\R", LS + LINE_PREFIX));
        }
    }
}
