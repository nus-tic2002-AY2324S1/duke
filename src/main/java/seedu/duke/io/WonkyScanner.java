package seedu.duke.io;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeScannerException;
import seedu.duke.task.WonkyManager;

public class WonkyScanner {

    private static Command currCommand;
    private static Scanner in;
    private static boolean isActive = true;

    public static void startUp() throws DukeException {
        if (Objects.isNull(in)) {
            in = new Scanner(System.in);
        }
        while (isActive) {
            String nextLine = in.nextLine().trim();
            processNextLine(nextLine);
        }
    }

    public static void bye() throws DukeException {
        if (WonkyLogger.isLoading) {
            WonkyLogger.byeInStorage();
        } else {
            isActive = false;
            shutdown();
        }
    }

    public static String typoSuggestion(String invalidCmd) {
        for (Command cmd : Command.values()) {
            String cmdStr = cmd.getLitr();
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

    public static boolean processNextLine(String nextLine) throws DukeException {
        try {
            final List<String> SPLIT_LN = Arrays.asList(nextLine.split(" ", 2));
            final String INPUT_CMD = SPLIT_LN.get(0);
            try {
                currCommand = Command.getEnum(INPUT_CMD);
                String currArgument = "";
                if (SPLIT_LN.size() == 2) {
                    currArgument = SPLIT_LN.get(1);
                }
                if (Objects.nonNull(currCommand)) {
                    WonkyManager.executeCommand(new CommandArgument(currCommand, currArgument));
                }
            } catch (IllegalArgumentException e) {
                WonkyLogger.unknownCommand(INPUT_CMD);
                WonkyLogger.suggestCommand(typoSuggestion(INPUT_CMD));
            } catch (IndexOutOfBoundsException e) {
                WonkyLogger.mismatchArgs(INPUT_CMD);
                WonkyLogger.suggestCommand(typoSuggestion(INPUT_CMD));
            }
        } catch (DukeException e) {
            throw e;
        } 
        // catch (Exception e) {
        //     System.out.println(e);
        //     throw new DukeScannerException(e.getMessage());
        // }
        return true;
    }

    private static void shutdown() throws DukeException {
        WonkyLogger.bye();
        if (Objects.nonNull(in)) {
            in.close();
        }
    }
}
