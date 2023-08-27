package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeUnhandledException;
import seedu.duke.logger.WonkyLogger;
import seedu.duke.logger.WonkyScanner;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            initialise();
            List<Task> tasks = new ArrayList<>();
            while (WonkyScanner.isActive()) {
                if (WonkyScanner.islastCmdValid()) {
                    if (WonkyScanner.userIsList()) {
                        WonkyLogger.postList();
                        for (Task task : tasks) {
                            WonkyLogger.task(task.getStatusMsg());
                        }
                    } else {
                        Deadline newTask = new Deadline(" : " + WonkyScanner.getCmdStr(), "27/08/2023");
                        WonkyLogger.preList(WonkyScanner.getCmdStr());
                        tasks.add(newTask);
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
