package task;
public class Todo extends Task {
    /**
     * Constructor of todo take in description , the 'isDone' status set to false and the tag is set to T
     *
     * @param description description of todo
     */
    public Todo(String description) {
        super(description);
        isDone = false;
        tag = "T";
    }

    /**
     *
     * @return string format of todo
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
