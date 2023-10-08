public class Event extends Task {

    protected String doneFrom;
    protected String doneTo;


    public Event(String description, String from, String to){
        super(description);
        this.doneFrom = from;
        this.doneTo = to;
        this.isDone = false;
        this.type = "Event";
    }


    public String toString() {
        return "[Event] [" + getIsDone() + "] " + this.taskDescription + " (" + this.doneFrom + " " + this.doneTo + ")";
    }
}
