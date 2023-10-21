package duke.task;

import duke.exception.InvalidArgumentException;
public class Deadline extends Task {
    protected String by;

    public Deadline(boolean isDone, String description, String by){
        super(description);
        setAbbreviation();
        setDone(isDone);
        this.by = by;
    }

    public void execute()throws InvalidArgumentException {
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'D';
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s (by: %s)",abbreviation,getStatusIcon(),dukeDescription, by);
    }
    public String getBy() {
        return by;
    }
}
