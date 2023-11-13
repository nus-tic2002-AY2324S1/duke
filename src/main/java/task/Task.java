package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    private boolean isDone;
    private String description;

    public Task (String d,boolean x){
        this.isDone = x ;
        setDescription(d);
    }


    public void setIsDone(boolean x){
        this.isDone = x;
    }

    public void setDescription(String description){
        String[] words = description.split(" ", 2);
        try {
            this.description = words[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }

    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription(){
        return description;
    }

    public String toTextFile(){
       String result = "";
        if (!isDone){
            result = "[T][ ] "+ getDescription();
        }else {
            result = "[T][X] "+ getDescription();
        }
       return result;
    }

    public void printTask(){
        if (!isDone){
            System.out.println("[T][ ] "+ getDescription());
        }else {
            System.out.println("[T][X] "+ getDescription());
        }

    }

}
