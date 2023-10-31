public class Deadline extends Todo {
    protected String due;

    Deadline(String description, String by) {
        super(description);
        due = by;
    }

    public void setBy(String change) {
        due = change;
    }

    public String getBy() {
        return due;
    }
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by:" + getBy() + ")";
    }
}

