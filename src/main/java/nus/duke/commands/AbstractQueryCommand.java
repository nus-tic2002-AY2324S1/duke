package nus.duke.commands;

/**
 * The `AbstractTaskCommand` class serves as the base class for commands that query tasks.
 */
public abstract class AbstractQueryCommand extends AbstractCommand {
    /**
     * Instantiates a new `AbstractTaskCommand` with the provided arguments.
     *
     * @param args The user input arguments.
     */
    protected AbstractQueryCommand(String args) {
        super(args);
    }
}
