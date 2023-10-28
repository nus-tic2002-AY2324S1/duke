package duke.command;

public class RecurCommand extends IndexBaseCommand{
    public static final String COMMAND_WORD = "recur";
    public static final String EXAMPLE_USAGE = "Example of usage:\nrecur 1";
    public static final String RECUR_ERROR_MESSAGE = "The RECUR command is only applicable to event tasks!";
    public static final String RECURRING_EVENT_MESSAGE  = "Great! I've set this event to recur.";


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
