package amebot.tasks;

public class Deadline extends Task {
    protected final String DEADLINE = "[DEADLINE] ";
    protected String dueDateTime;

    public Deadline(boolean isSelected, String description, String dueDateTime) {
        super(isSelected, description);
        super.type = DEADLINE;
        this.dueDateTime = dueDateTime.toUpperCase();
    }

    @Override
    public String getTask() {
        return type + status + description + dueDateTime;
    }

    @Override
    public String getDueDateTime() {
        return dueDateTime;
    }
}
