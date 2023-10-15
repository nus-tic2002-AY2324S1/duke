package amebot.tasks;

public class Event extends Task {
    protected final String EVENT = "[EVENT] ";
    protected String fromDate;
    protected String toDate;

    public Event(String desc, String fromDate, String toDate) {
        super(desc);
        super.type = EVENT;
        this.fromDate = fromDate.toUpperCase();
        this.toDate = toDate.toUpperCase();
    }

    @Override
    public void printTask() {
        System.out.println(type + status + desc + fromDate + toDate);
    }
}
