package seedu.duke.logger;

import java.util.Objects;
import java.util.Scanner;

import seedu.duke.enums.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeScannerException;

public class WonkyScanner {

    private static Command cmd;
    private static Scanner in;
    private static boolean isLastCmdValid = false;

    public static void startUp() {
        in = new Scanner(System.in);
    }

    public static boolean nextLine() throws DukeException {
        try {
            final String nxtLn = in.nextLine().toUpperCase();
            try {
                if (Objects.nonNull(Command.valueOf(nxtLn))) {
                    isLastCmdValid = true;
                    cmd = Command.valueOf(nxtLn);
                }
            } catch (IllegalArgumentException e) {
                isLastCmdValid = false;
                WonkyLogger.unknownCommand(nxtLn);
                WonkyLogger.suggestCommand(typoSuggestion(nxtLn));
            }
        } catch (Exception e) {
                System.out.println(e);
                throw new DukeScannerException(e.getMessage());
        }
        return true;
    }
    
    public static boolean userNotExit() {
        return !Command.BYE.equals(cmd);
    }

    public static boolean userIsList() {
        return Command.LIST.equals(cmd);
    }

    public static String getCmdStr() {
        return cmd.toString();
    }

    public static void close() {
        if (Objects.nonNull(in)) {
            in.close();
        }
    }

    public static boolean isActive() throws DukeException {
        return nextLine() && userNotExit();
    }

    public static boolean islastCmdValid() {
        return isLastCmdValid;
    }

    public static String typoSuggestion(String invalidCmd) {
        for (Command cmd : Command.values()) {
            String cmdStr = cmd.toString();
            if (cmdStr.length() == invalidCmd.length()) {
                int matches = 0;
                for (int i = 0; i < cmdStr.length(); i += 1) {
                    if (cmdStr.charAt(i) == invalidCmd.charAt(i)) {
                        matches += 1;
                    }
                }
                if (matches == cmdStr.length() - 1) {
                    return cmdStr.toLowerCase();
                }
            }
        }
        return null;
    }
}
