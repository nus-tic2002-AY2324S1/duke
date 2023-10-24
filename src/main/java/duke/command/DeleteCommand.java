package duke.command;


public class DeleteCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String EXAMPLE_USAGE = "Example of usage:\ndelete 1";
    public static final String MESSAGE_REMOVED_TASK = "Noted. I've removed this task:";

    /**
     * Default constructor for the DeleteCommand class.
     */
    public DeleteCommand() {
    }

    /**
     * Gets the message indicating that a task has been removed.
     *
     * @return The message indicating the removal of a task.
     */
    @Override
    public String getMessage() {
        return MESSAGE_REMOVED_TASK;
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
