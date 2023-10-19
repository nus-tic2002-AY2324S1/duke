package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static duke.common.Messages.MSG_WELCOME;
import static duke.common.Messages.MSG_GOODBYE;

public class TextUI {
    private final Scanner in;
    private final PrintStream out;
    private static final String DIVIDER = "--------------------------------------------------";

    public TextUI(){ this (System.in,System.out); }

    public TextUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printWelcomeMsg(){
        printMsg(DIVIDER, MSG_WELCOME, DIVIDER);
    }

    public void printGoodbyeMsg(){
        printMsg(DIVIDER, MSG_GOODBYE, DIVIDER);
    }

    public void printMessage(String message) {
        printMsg(DIVIDER, message, DIVIDER);
    }

    public String getCommand (){
        out.print("Your instruction: ");
        String inputLine = in.nextLine();

        while (inputLine.isEmpty()) {
            inputLine = in.nextLine();
        }

        return inputLine;
    }

    private void printMsg(String... msg){
        for (String m : msg){
            System.out.println(m);
        }
    }
}
