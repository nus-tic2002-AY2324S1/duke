package duke.task;

import duke.exception.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;
    public Deadline(boolean isDone, String description, LocalDateTime byDateTime){
        super(description);
        setAbbreviation();
        setDone(isDone);
        this.byDateTime = byDateTime;
    }
    public void execute()throws InvalidArgumentException {
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'D';
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s (by: %s)",abbreviation,getStatusIcon(),dukeDescription, getByDateTime());
    }
    public String getByDateTime() {
        return byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm"));
    }
    public String getByDateTime(String pattern) {
        return byDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

}
