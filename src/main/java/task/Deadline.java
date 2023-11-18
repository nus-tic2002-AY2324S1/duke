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
    //private String description;
    public Deadline(String description, boolean isDone, String deadlineInformation) {
        //set description first time in the Task class
        super(description,isDone);

        //second time setting description
         extractBy(deadlineInformation);

    }
    public String getBy() {
        return by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }


    public void extractBy(String deadlineInformation) {
        String deadline = deadlineInformation; //--> "14/05/2023 1800"
        String newBy = dateParser.toDate(deadline);// --> "May 14 2023"
        assert deadline!=null:"Deadline has to be detailed with dates.";
        setBy(newBy); //--> this.by
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
