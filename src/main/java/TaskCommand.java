public abstract class TaskCommand extends Command {
    public TaskCommand(String args) {
        super(args);
    }

    protected static String[] getTaskAddedMessages(TaskList tasks) {
        return new String[]{
                "Got it. I've added this task:",
                "  " + tasks.getLastTask(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }
}
