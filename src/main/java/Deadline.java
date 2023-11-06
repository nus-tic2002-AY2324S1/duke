import java.time.LocalDateTime;

public class Deadline extends Todo {

    protected LocalDateTime due;

    Deadline(String description, LocalDateTime by) {
        super(description);
        due = by;
    }


    public String getBy() {
        return printDate(due);
    }
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by:" + getBy() + ")";
    }
}

