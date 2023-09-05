package seedu.duke.logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeScannerException;
import seedu.duke.task.Deadline;
import seedu.duke.task.WonkyManager;

public class WonkyScanner {

    private static Command currCommand;
    private static Scanner in;
    private static boolean isLastCmdValid = false;

    public static void startUp() {
        if (Objects.isNull(in)) {
            in = new Scanner(System.in);
        }
    }

    public static boolean nextLine() throws DukeException {
        try {
            final String nxtLn = in.nextLine();
            final List<String> splitLn = Arrays.asList(nxtLn.split(" "));
            final String inputCmd = splitLn.get(0).toLowerCase();
            try {
                currCommand = Command.valueOf(inputCmd);
                final String currArgument = String.join(" ", splitLn.subList(1, splitLn.size() - 1));
                if (Objects.nonNull(currCommand)) {
                    isLastCmdValid = true;
                    WonkyManager.addTask(currCommand, currArgument);
                }
            } catch (IllegalArgumentException e) {
                isLastCmdValid = false;
                WonkyLogger.unknownCommand(inputCmd);
                WonkyLogger.suggestCommand(typoSuggestion(inputCmd));
            } catch (IndexOutOfBoundsException e) {
                isLastCmdValid = false;
                WonkyLogger.missingArgs(inputCmd);
                WonkyLogger.suggestCommand(typoSuggestion(inputCmd));
            }
        } catch (Exception e) {
                System.out.println(e);
                throw new DukeScannerException(e.getMessage());
        }
        return true;
    }
    
    private static boolean userNotExit() {
        return Objects.nonNull(currCommand) && !Command.BYE.equals(currCommand);
    }

    public static boolean userIsList() {
        return Objects.nonNull(currCommand) && Command.LIST.equals(currCommand);
    }

    public static String getCmdStr() {
        return currCommand.command();
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
            String cmdStr = cmd.command();
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
