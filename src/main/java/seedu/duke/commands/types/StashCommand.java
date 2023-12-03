package seedu.duke.commands.types;

import java.util.List;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyStorage;
import seedu.duke.task.WonkyManager;

/**
 * Represents a stash command that allows users to add, apply, pop, clear, and list stashes.
 * Inherits from the CommandType class.
 * Contains methods to execute the stash command.
 */
public class StashCommand extends CommandType {

    private static final int ZERO_IDX = 0;
    private static final int ONE_IDX = 1;

    private static final int ONE_ARGS = 1;
    private static final int TWO_ARGS = 2;

    public StashCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        WonkyStorage wonkyStorage = WonkyStorage.getInstance();
        WonkyLogger wonkyLogger = WonkyLogger.getInstance();
        List<String> cmdList = this.getArgList();
        List<String> stashList = wonkyStorage.getStashList();
        if (validateArgs(this, ONE_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            if (firstArg.equals("list")) {
                wonkyLogger.printStashList(stashList);
            } else if (firstArg.equals("clear")) {
                wonkyStorage.clearStash();
                wonkyLogger.stashCleared();
            } else {
                wonkyLogger.invalidStashCommand(firstArg);
            }
        } else if (validateArgs(this, TWO_ARGS, false)) {
            String firstArg = cmdList.get(ZERO_IDX).trim();
            String stashName = cmdList.get(ONE_IDX).trim();
            if (firstArg.equals("add")) {
                wonkyStorage.addStash(stashName, WonkyManager.getInstance().getCmdTypes());
                wonkyLogger.stashAdded(stashName);
            } else if (!stashList.contains(stashName)) {
                wonkyLogger.invalidStashName(stashName);
            } else if (firstArg.equals("apply")) {
                wonkyStorage.applyStash(stashName);
                wonkyLogger.stashApplied(stashName);
            } else if (firstArg.equals("pop")) {
                wonkyStorage.popStash(stashName);
                wonkyLogger.stashPopped(stashName);
            } else {
                wonkyLogger.invalidStashCommand(firstArg);
            }
        }
    }
}
