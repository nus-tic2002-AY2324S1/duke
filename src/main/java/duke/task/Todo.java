package duke.task;

import duke.exception.InvalidArgumentException;

public class Todo extends Task {
    /**
     * Construction
     */
    public Todo(){
        isDone = false;
    }

    /**
     * Construction of Todo task take in description sets its abbreviation
     * @param description The description for the Todo task.
     */
    public Todo(String description)  {
        super(description);
        setAbbreviation();
    }

    /**
     * Construction of Todo task take in description and status of the task sets its abbreviation.
     * @param isDone The status of the task.
     * @param description The description for the Todo task.
     */
    public Todo(boolean isDone, String description){
        super(description);
        setAbbreviation();
        setDone(isDone);
    }

    public void execute()throws InvalidArgumentException {
        response();
    }

    /**
     * set abbreviation 'T'
     */
    public void setAbbreviation() {
        abbreviation = 'T';
    }

    

    /**
     * Override toString method to print detail relevant to task.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s",abbreviation,getStatusIcon(),dukeDescription);
    }

}
