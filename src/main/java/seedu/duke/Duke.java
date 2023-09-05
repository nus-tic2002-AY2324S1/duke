package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeUnhandledException;
import seedu.duke.logger.WonkyLogger;
import seedu.duke.logger.WonkyScanner;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.WonkyManager;

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            initialise();
            while (WonkyScanner.isActive()) {
                if (WonkyScanner.islastCmdValid()) {
                    if (WonkyScanner.userIsList()) {
                        // WonkyLogger.printListTitle();
                        // // for (Task task : tasks) {
                        // //     WonkyLogger.task(task.getStatusMsg());
                        // // }
                    }
                }
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
