public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
    
    @Override
    public String getPrintStatus() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}