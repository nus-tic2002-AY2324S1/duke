package duke.task;

import duke.exception.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;
    public Event(String description) {
        super(description);
        setAbbreviation();
    }
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to){
        super(description);
        setAbbreviation();
        setDone(isDone);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    public void execute() throws InvalidArgumentException {
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'E';
    }


    @Override
    public String toString(){
        return String.format("[%c][%s] %s (from: %s to: %s)",abbreviation,getStatusIcon(),dukeDescription, getFromDateTime(), getToDateTime());
    }
    public String getFromDateTime() {
        return fromDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DISPLAY));
    }
    public String getToDateTime() {
        return toDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DISPLAY));
    }

    public String getFromDateTime(String pattern) {
        return fromDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public String getToDateTime(String pattern) {
        return toDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
