package ui;

import common.Title;
import common.Messages;

import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    public void printWelcome() {
        Title.printHeader();
        Title.printGreeting();
    }

    public String getInputCommand() {
        Scanner scanInput = new Scanner(System.in);
        return scanInput.nextLine().toLowerCase();
    }

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
