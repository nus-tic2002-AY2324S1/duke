package task;

import parser.DateTimeParser;

public class Event extends Task{
    private static DateTimeParser dateParser;
    private String from;
    private String to;

    //variable x stands for isDone
    public Event(String description, boolean isDone, String eventFrom, String eventTo) {
        super(description,isDone);
        setFrom(eventFrom);
        setTo(eventTo);
    }


    public void extractFromAndTo(String description) {
        String[] words = description.split(" ", 2);
        try {
            String[] splitFrom = words[1].split("/from ");
            String[] splitTo = splitFrom[1].split("/to ");

        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }
    }

    //TODO: another use case  "Event <description> <date> from <time>  to <time>"
    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

    public void setTo(String newTo) {
        this.to = newTo;
    }

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    @Override
    public String getTask(){
        String result = "";
        if (getIsDone()){
            result = "[E][ ] "+ getDescription() + "(from: " + getFrom() + "to: "+ getTo() +")";
        }else {
            result = "[E][X] "+ getDescription() + "(from: " + getFrom() + "to: "+ getTo() +")";
        }
        return result;
    }

    @Override
    public void printTask(){
        System.out.println(getTask());

    }
}
