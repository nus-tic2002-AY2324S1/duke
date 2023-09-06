package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeUnhandledException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;
import seedu.duke.task.WonkyManager;

// TODO
// trim description / other attributes when storing for list

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            initialise();
            while (WonkyScanner.isActive()) {
                WonkyManager.manage();
            }
            shutdown();
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeUnhandledException(e.getMessage());
        }
        System.exit(0);
    }

    private static void initialise() throws DukeException {
        WonkyLogger.startUp();
        WonkyScanner.startUp();
    }

    private static void shutdown() throws DukeException {
        WonkyLogger.bye();
        WonkyScanner.close();
    }
}
