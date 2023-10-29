package duke.command;

/**
 * The UnMarkCommand class represents a command that unmarks a previously marked task.
 */
public class UnMarkCommand extends MarkCommand {
    public static final String COMMAND_WORD = "unmark";
    public static final String EXAMPLE_USAGE = "Example of usage:\nunmark 1";
    public static final String UNMARK_THE_TASK = "OK, I've marked this task as not done yet:";

    /**
     * Default constructor for the UnMarkCommand class.
     * Constructs a new instance of the UnMarkCommand class with the 'isMark' flag set to 'false'.
     */
    public UnMarkCommand() {
        isMark = false;
    }

    /**
     * Retrieves the message indicating that a task has been unmarked (marked as not done).
     *
     * @return A string representing the message for unmarking a task.
     */
    @Override
    public String getMessage() {
        return UNMARK_THE_TASK;
    }

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string demonstrating how to use the command.
     */
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

}
