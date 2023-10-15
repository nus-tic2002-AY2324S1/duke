package tasks;

public class ToDo extends Task {
    protected final String TODO = "[TODO] ";

    public ToDo(boolean isSelected, String description) {
        super(isSelected, description);
        super.type = TODO;
    }

    @Override
    public String getTask() {
        return type + status + description;
    }
}
