package seedu.duke.io;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeScannerException;
import seedu.duke.task.WonkyManager;

/**
 * Handles reading user input and processing it into commands to be executed by the program.
 */
public class WonkyScanner {

    private CommandEnum currCommand;
    private boolean isActive = true;

    private static WonkyScanner wonkyScanner;
    private WonkyLogger wonkyLogger;
    private WonkyManager wonkyManager;

    public WonkyScanner() {}

    public static WonkyScanner getInstance() {
        if (wonkyScanner == null) {
            wonkyScanner = new WonkyScanner();
        }
        return wonkyScanner;
    }

    public void setReferences(WonkyLogger wonkyLogger, WonkyManager wonkyManager) {
        this.wonkyLogger = wonkyLogger;
        this.wonkyManager = wonkyManager;
    }

    /**
     * Exits the program.
     *
     * @throws DukeException If there is an error with the logger or scanner.
     */
    public void bye() throws DukeException {
        if (wonkyLogger.getLoading()) {
            wonkyLogger.byeInStorage();
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
    public String typoSuggestion(String invalidCmd) {
        for (CommandEnum cmd : CommandEnum.values()) {
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
    public void processNextLine(String nextLine) throws DukeException {
        if (!isActive) {
            return;
        }
        try {
            final List<String> splitLn = Arrays.asList(nextLine.split(" ", 2));
            final String inputCmd = splitLn.get(0);
            try {
                currCommand = CommandEnum.getEnum(inputCmd);
                String currArgument = "";
                if (splitLn.size() == 2) {
                    currArgument = splitLn.get(1);
                }
                if (Objects.nonNull(currCommand)) {
                    wonkyManager.checkCommand(currCommand, currArgument);
                }
            } catch (IllegalArgumentException e) {
                wonkyLogger.unknownCommand(inputCmd);
                wonkyLogger.suggestCommand(typoSuggestion(inputCmd));
            } catch (IndexOutOfBoundsException e) {
                wonkyLogger.mismatchArgs(inputCmd);
                wonkyLogger.suggestCommand(typoSuggestion(inputCmd));
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
    public void shutdown() throws DukeException {
        wonkyLogger.bye();

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean bool) {
        isActive = bool;
    }
}
