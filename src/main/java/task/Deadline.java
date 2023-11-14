package task;

import parser.DateTimeParser;

import java.time.LocalDate;

/**
 *  Represent a <code>Deadline</code> object that extends an instance from <code>Task</code> object
 *  that comes with date as deadline.
 */
public class Deadline extends Task{

    private static DateTimeParser dateParser;
    private String by;
    private String description;
    public Deadline(String d,boolean x) {
        super(d,x);
        extractBy(d);
    }
    public String getBy() {
        return by;
    }

    public void setBy( String newBy) {
        this.by = newBy;
    }

    public void extractBy(String description) {
        String[] words = description.split(" ", 2);
        try {
            String[] splitBy = words[1].split("/by ");
            //setDescription(splitBy[0]);
            String deadline = splitBy[1];
            String newBy = dateParser.toDate(deadline);
            setBy(newBy);
        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }
    }

    @Override
    public String getTask(){
        String result = "";
        if (getIsDone()){
            result = "[D][ ] "+ getDescription() + "(by: "+getBy()+")";
        }else {
            result = "[D][X] "+ getDescription() + "(by: "+getBy()+")";
        }
        return result;
    }


    @Override
    public void printTask(){
        System.out.println(getTask());
    }
}
