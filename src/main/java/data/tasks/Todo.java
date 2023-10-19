package data.tasks;

public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String encode() {
        return String.format("T | %s | %s", encodeIsDone(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
