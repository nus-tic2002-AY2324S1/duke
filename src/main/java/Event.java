public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String printItemWithStatus() {
        return "[E]" + super.printItemWithStatus() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
