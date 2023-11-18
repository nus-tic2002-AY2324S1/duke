package duke.command;

/**
 * The DeleteCommand class represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Deletes a task from the list based on the " +
            "provided index.";
    public static final String EXAMPLE_USAGE = COMMAND_DESCRIPTION + "\n" +
            "Parameter: INDEX\n" +
            "Example of usage: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_REMOVED_TASK = "Noted. I've removed this task:";

    public DeleteCommand() {
    }

    /**
     * @return The message indicating the removal of a task.
     * @inheritDoc Gets the message indicating that a task has been removed.
     */
    @Override
    public String getMessage() {
        return MESSAGE_REMOVED_TASK;
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
