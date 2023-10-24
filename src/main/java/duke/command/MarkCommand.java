package duke.command;

public class MarkCommand extends IndexBaseCommand {
    public static final String COMMAND_WORD = "mark";
    public static final String EXAMPLE_USAGE = "Example of usage:\nmark 1";
    public static final String MARK_TASK_DONE = "Nice! I've marked this task as done:";
    protected boolean isMark;

    /**
     * Default constructor for the MarkCommand class.
     * Constructs a new instance of the MarkCommand class with the 'isMark' flag set to 'true'.
     */
    public MarkCommand() {
        this.isMark = true;
    }

    /**
     * Retrieves the message indicating that a task has been marked as done.
     *
     * @return A string representing the message for marking a task as done.
     */
    @Override
    public String getMessage() {
        return MARK_TASK_DONE;
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

    /**
     * Checks if a task should be marked as done.
     *
     * @return 'true' if the task should be marked as done, 'false' otherwise.
     */
    public boolean isMark() {
        return isMark;
    }

}
