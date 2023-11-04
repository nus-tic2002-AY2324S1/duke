package duke.command;

/**
 * The MarkCommand class represents a command that marks a task at a specific index as done.
 */
public class MarkCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "mark";
    public static final String EXAMPLE_USAGE = "Example of usage:\nmark 1";
    public static final String MARK_TASK_DONE = "Nice! I've marked this task as done:";
    protected boolean isMark;

    public MarkCommand() {
        this.isMark = true;
    }

    /**
     * @return A string representing the message for marking a task as done.
     * @inheritDoc Retrieves the message indicating that a task has been marked as done.
     */
    @Override
    public String getMessage() {
        return MARK_TASK_DONE;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    /**
     * Checks if a task should be marked as done.
     *
     * @return 'true' if the task should be marked as done, 'false' otherwise.
     */
    public boolean isMark() {
        return isMark;
    }

}
