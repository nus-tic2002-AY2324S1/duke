package seedu.duke.commands.types;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyScanner;

/**
 * Represents a command to exit the program.
 * Inherits from the CommandType abstract class.
 * Executes the command by calling the bye() method of the WonkyScanner class.
 * @see CommandType
 * @see WonkyScanner
 */
public class ByeCommand extends CommandType {

    private static final int ZERO_ARGS = 0;

    public ByeCommand(CommandEnum command, String arguments) {
        super(command, arguments);
    }

    @Override
    public void execute() throws DukeException {
        if (super.validateArgs(this, ZERO_ARGS)) {
            WonkyScanner.getInstance().bye();
        }
    }
}
