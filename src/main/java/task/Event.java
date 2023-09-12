package task;

public class Event extends Task {
    protected String time;
    protected String time1;



    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, String time1) {
        super(description);
        this.time = time;
        this.time1 = time1;
    }

    @Override
    public String toString() {
        if (time1==null) {
            return "[E]" + super.toString() + "(from:" + time + ")";
        }
        return "[E]" + super.toString() + "(from:" + time + "➞ to:" + time1 + ")";
    }

}