package tasks;

public class Deadline extends Task {
    protected final String DEADLINE = "[DEADLINE] ";
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        super.type = DEADLINE;
        this.dueDateTime = dueDateTime.toUpperCase();
    }

    @Override
    public String getTask() {
        return type + status + description + dueDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }
}
