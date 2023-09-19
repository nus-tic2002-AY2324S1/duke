public class Deadlines extends Task{

    public Deadlines(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

}
