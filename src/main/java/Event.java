public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description,String from,String to) {
        super(description);
        isDone = false;
        this.from = from;
        this.to = to;
    }
    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean change){
        this.isDone = change;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(from:" + getFrom() + " to:" + getTo() + ")";
    }
}
