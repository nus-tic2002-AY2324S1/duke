public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDisplay() {
        return "[E]" + super.toDisplay() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | " + from + " | " + to;
    }
}
