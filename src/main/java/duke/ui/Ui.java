package duke.ui;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.common.Message;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    public static final String DIVIDER = "=".repeat(80);
    private static final String NEWLINE = System.lineSeparator();
    private final static int INDENT_RESPONSE = 5;
    private final static int INDENT_LINE = 4;

    public Ui(){
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }
    public void showLoadingError(String message) {
        showResponseToUser(message);
    }

    /**
     * prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        showToUser(
                prefixString(INDENT_LINE) + DIVIDER,
                logoString(),
                prefixString(INDENT_LINE) + DIVIDER,
                prefixString(INDENT_RESPONSE) + "Hello! I'm Luna",
                prefixString(INDENT_RESPONSE) + "What can I do for you?",
                prefixString(INDENT_LINE) + DIVIDER);
    }

    public String getUserCommand(){
        return in.nextLine();
    }

    /**
     * print divider line
     */
    public void showLine() {
        out.println(prefixString(INDENT_LINE) + DIVIDER);
    }


    private static String logoString() {
        String logo;
        logo =    "      _                    \n"
                + "     | |    _   _ _  __     __\n"
                + "     | |   | | | | | __  \\/ __ \\\n"
                + "     | |___| |_| | |   | | |__| |\n"
                + "     |_____|\\__,_|_|   |_|_|  |_|\n";
        return logo;
    }

    private static String prefixString(int n){
        return(" ".repeat(n));
    }

    public static String prefixRespString(){
        return prefixString(INDENT_RESPONSE);
    }

    public static String prefixLineString(){
        return prefixString(INDENT_LINE);
    }

    public void showToUser(String... message){
        for (String m : message) {
            out.println(m.replace("\n",NEWLINE));
        }
    }

    public void showResponseToUser(ArrayList<String> message){
        showLine();
        for (String m : message) {
            out.println(prefixRespString() + m.replace("\n",NEWLINE + prefixRespString()));
        }
        showLine();
    }

    public void showResponseToUser(String... message){
        showLine();
        for (String m : message) {
            out.println(prefixRespString() + m.replace("\n",NEWLINE + prefixRespString()));
        }
        showLine();
    }
}
