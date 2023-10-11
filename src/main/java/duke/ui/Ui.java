package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private static final String DIVIDER = "=========================================================";
    private static final String NEWLINE = System.lineSeparator();
    private static final String LINE_PREFIX = "    ";

    public Ui(){
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }
    public void showLoadingError() {
    }

    public void showeWelcome() {
        showToUser(DIVIDER,
                "Hello! I'm Luna",
                "What can I do for you?",
                logo(),
                DIVIDER);
    }

    public String readCommand() {
        return "";
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
    }

    private static String logo() {
        String logo;
        logo =    "      _                    \n"
                + "     | |    _   _ _  __     __\n"
                + "     | |   | | | | | __  \\/ __ \\\n"
                + "     | |___| |_| | |   | | |__| |\n"
                + "     |_____|\\__,_|_|   |_|_|  |_|\n";
        return logo;
    }

    public void showToUser(String... message){
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n",NEWLINE + LINE_PREFIX));
        }
    }
}
