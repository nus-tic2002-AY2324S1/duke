package seedu.duke;

import java.util.Arrays;
import java.util.List;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeUnhandledException;
import seedu.duke.helper.WonkyMode;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyStorage;

/**
 * Controls the flow of the Wonky chatbot application.
 */
public class Duke {

    /**
     * Starts the Wonky chatbot application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) throws DukeException {
        WonkyMode mode = checkMode(args);
        try {
            initialise(mode);
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeUnhandledException(e);
        }
    }

    /**
     * Performs startup operations for the Wonky chatbot application based on the specified mode.
     *
     * @param mode The mode in which the application should operate.
     * @throws DukeException If there is an error during startup.
     */
    public static void initialise(WonkyMode mode) throws DukeException {
        WonkyStorage.getInstance().startUp(mode);
        WonkyLogger.getInstance().startUp(mode);
    }

    /**
     * Checks the mode to start the Wonky chatbot application based on the input args.
     *
     * @param args The command line arguments.
     */
    public static WonkyMode checkMode(String[] args) {
        List<String> argList = Arrays.asList(args);
        if (argList.size() > 0) {
            try {
                return WonkyMode.getEnum(argList.get(0));
            } catch (IllegalArgumentException e) {
                return WonkyMode.NORMAL;
            }
        }
        return WonkyMode.NORMAL;
    }
}
