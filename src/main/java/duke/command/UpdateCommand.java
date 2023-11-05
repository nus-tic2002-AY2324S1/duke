package duke.command;

public class UpdateCommand extends IndexBaseCommand{
    public static final String COMMAND_WORD = "update";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " 1";
    public static final String RECUR_ERROR_MESSAGE = "The RECUR command is only applicable to event tasks!";
    public static final String RECURRING_EVENT_MESSAGE = "Great! I've scheduled this event to repeat every week for " +
            "the next three months.";
    /**
     * This abstract method returns a message associated with the implementing class. Subclasses should override this
     * method to provide specific messages.
     *
     * @return A string representing the message for a task.
     */
    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getCommandWord() {
        return null;
    }

    @Override
    public String getExampleUsage() {
        return null;
    }
}
