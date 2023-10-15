public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String encode() {
        return String.format("E | %s | %s | %s-%s", encodeIsDone(), description, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
