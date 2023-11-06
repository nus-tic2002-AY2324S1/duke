import java.time.LocalDateTime;

public class Deadline extends Todo {

    protected LocalDateTime by;

    Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        tag = "D";
    }


    public String getBy() {
        return printDate(by);
    }
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by:" + getBy() + ")";
    }
    @Override
    public LocalDateTime endTime(){
        return by;
    }

}

