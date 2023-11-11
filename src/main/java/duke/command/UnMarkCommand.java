package duke.command;

/**
 * The UnMarkCommand class represents a command that unmarks a previously marked task.
 */
public class UnMarkCommand extends MarkCommand {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Marks a task as incomplete in the list using " +
            "the specified index.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter : INDEX\n" +
            "Example of usage: " + COMMAND_WORD + " 1";
    public static final String UNMARK_THE_TASK = "OK, I've marked this task as not done yet:";

    public UnMarkCommand() {
        isMark = false;
    }

    /**
     * @return A string representing the message for unmarking a task.
     * @inheritDoc Retrieves the message indicating that a task has been unmarked (marked as not done).
     */
    @Override
    public String getMessage() {
        return UNMARK_THE_TASK;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

}
