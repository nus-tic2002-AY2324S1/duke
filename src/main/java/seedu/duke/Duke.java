package seedu.duke;

import java.util.Arrays;
import java.util.List;

import seedu.duke.commands.WonkyMode;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeUnhandledException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        WonkyMode mode = checkMode(args);
        try {
            initialise(mode);
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeUnhandledException(e.getMessage());
        }
        System.exit(0);
    }

    private static void initialise(WonkyMode mode) throws DukeException {
        WonkyLogger.startUp(mode);
        WonkyScanner.startUp();
    }

    private static WonkyMode checkMode(String[] args) {
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
