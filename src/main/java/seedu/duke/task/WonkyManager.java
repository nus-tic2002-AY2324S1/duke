package seedu.duke.task;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.logger.WonkyLogger;
import seedu.duke.logger.WonkyScanner;

public class WonkyManager {
    private static List<Task> tasks;

    public static void addTask(Command cmd, String args) throws DukeException {
        switch (cmd) {
            case LIST:
                WonkyLogger.printListTitle();
                for (Task task : tasks) {
                    WonkyLogger.task(task.getStatusMsg());
                }
            break;
            case TODO:
            break;
            case DEADLINE:
            break;
            case EVENT:
            break;
            default:
            break;
        }
        WonkyLogger.addedToList(WonkyScanner.getCmdStr());
    }
}