public class Events extends Task{

    public Events(String description){
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }
}
