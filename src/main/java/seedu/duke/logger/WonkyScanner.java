package seedu.duke.logger;

import java.util.Objects;
import java.util.Scanner;

import seedu.duke.exceptions.DukeScannerException;

public class WonkyScanner {

    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";

    private static String line;
    private static Scanner in;

    public static void startUp() {
        in = new Scanner(System.in);
    }

    public static boolean nextLine() throws DukeScannerException {
        try {
            line = in.nextLine();
        } catch (Exception e) {
            throw new DukeScannerException(e.getMessage());
        }
        return true;
    }
    
    public static boolean userNotExit() {
        return !EXIT_CMD.equalsIgnoreCase(line);
    }

    public static boolean userIsList() {
        return LIST_CMD.equalsIgnoreCase(line);
    }

    public static String getLine() {
        return line;
    }

    public static void close() {
        if (Objects.nonNull(in)) {
            in.close();
        }
    }
}
