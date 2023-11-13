package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import parser.DateTimeParser;
import storage.Storage;

/**
 * A class handle task with deadlines for multiple formats in Java.
 */
public class Deadline extends Task{

    private static DateTimeParser dateParser;
    private String by;
    public Deadline(String d,boolean x) {
        super(d,x);
        extractBy(d);
    }
    public String getBy() {
        return by;
    }
    public void setBy(String newBy) {
        by = dateParser.toDate(newBy);
    }

    public void extractBy(String description) {
        String[] words = description.split(" ", 2);
        try {
            String[] splitBy = words[1].split("/by ");
            setDescription(splitBy[0]);
            String deadline = splitBy[1];
            this.by = dateParser.toDate(deadline);
        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }
    }

    @Override
    public String toTextFile(){
        String result = "";
        if (!getIsDone()){
            result = "[D][ ] "+ getDescription() + "(by: "+getBy()+")";
        }else {
            result = "[D][X] "+ getDescription() + "(by: "+getBy()+")";
        }
        return result;
    }

    @Override
    public void printTask(){
        if (!getIsDone()){
            System.out.println("[D][ ] "+ getDescription() + "(by: "+getBy()+")");
        }else {
            System.out.println("[D][X] "+ getDescription() + "(by: "+getBy()+")");
        }

    }
}
