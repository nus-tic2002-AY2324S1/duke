package seedu.duke.commands.types;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;

public class HelpCommand extends CommandType {

    private final int ZERO_ARGS = 0;

    public HelpCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, ZERO_ARGS)) {
            WonkyLogger.getInstance().printHelpCommand();
        }
    }
    
}
