package seedu.duke.io;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeScannerException;
import seedu.duke.task.WonkyManager;

/**
 * Handles reading user input and processing it into commands to be executed by the program.
 */
public class WonkyScanner {

    private static Command currCommand;
    private static boolean isActive = true;

    /**
     * Exits the program.
     *
     * @throws DukeException If there is an error with the logger or scanner.
     */
    public static void bye() throws DukeException {
        if (WonkyLogger.getLoading()) {
            WonkyLogger.byeInStorage();
        } else {
            shutdown();
        }
    }

    /**
     * Returns a suggested command based on a user's input with a typo.
     *
     * @param invalidCmd The user's input with a typo.
     * @return The suggested command, or null if there is no suggestion.
     */
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

    /**
     * Converts a user's input into a command to be executed.
     *
     * @param nextLine The user's input.
     * @throws DukeException If there is an error with the logger, scanner, or executing a command.
     */
    public static void processNextLine(String nextLine) throws DukeException {
        if (!isActive) {
            return;
        }
        try {
            final List<String> splitLn = Arrays.asList(nextLine.split(" ", 2));
            final String inputCmd = splitLn.get(0);
            try {
                currCommand = Command.getEnum(inputCmd);
                String currArgument = "";
                if (splitLn.size() == 2) {
                    currArgument = splitLn.get(1);
                }
                if (Objects.nonNull(currCommand)) {
                    WonkyManager.executeCommand(new CommandArgument(currCommand, currArgument));
                }
            } catch (IllegalArgumentException e) {
                WonkyLogger.unknownCommand(inputCmd);
                WonkyLogger.suggestCommand(typoSuggestion(inputCmd));
            } catch (IndexOutOfBoundsException e) {
                WonkyLogger.mismatchArgs(inputCmd);
                WonkyLogger.suggestCommand(typoSuggestion(inputCmd));
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeScannerException(e);
        }
    }

    /**
     * Logs a goodbye message.
     *
     * @throws DukeException If there is an error with the logger or scanner.
     */
    public static void shutdown() throws DukeException {
        WonkyLogger.bye();

        // Create a Timer
        Timer timer = new Timer();

        // Schedule a TimerTask to call System.exit(0) after 5 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000); // Delay in milliseconds
    }

    public static boolean isActive() {
        return isActive;
    }

    public static void setActive(boolean bool) {
        isActive = bool;
    }
}
