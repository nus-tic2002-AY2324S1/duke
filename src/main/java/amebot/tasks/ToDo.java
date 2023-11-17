package amebot.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    protected static final String TODO = "[TODO] ";

    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
        super.type = TODO;
    }

    /**
     * Returns information of the task.
     *
     * @return Information of the task.
     */
    @Override
    public String getTask() {
        return type + status + description;
    }
}
