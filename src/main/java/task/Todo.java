package task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        isDone = false;
        tag = "T";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
