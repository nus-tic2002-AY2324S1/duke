package amebot;

import java.util.Scanner;

import amebot.exceptions.AmebotException;
import amebot.tasks.commands.Command;

public class App {
    public static final String TOP_BORDER = "________________________________________________________________________________";
    public static final String BOTTOM_BORDER = "--------------------------------------------------------------------------------";
    private static boolean isLaunch = true;

    public static void launch() {
        Scanner scanInput = new Scanner(System.in);
        String input = "";

        while (App.isLaunch) {
            try {
                input = scanInput.nextLine().toLowerCase();
                Command.render(input);
            } catch (AmebotException err) {
                System.out.print("\n");
            }
        }
    }

    public static void exit() {
        isLaunch = false;

        System.out.println(TOP_BORDER);
        System.out.println("Thanks for using AMEBOT~!");
        System.out.println(BOTTOM_BORDER + "\n");
    }
}
