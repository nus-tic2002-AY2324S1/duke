package tim.tasks;

public class Event extends Task {
    private String from;
    private String to;
    public Event(String description, String from, String to){
        super(description);
        this.setType('E');
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription(){
        return (super.getDescription() + "(from: " + from + " to: " + to +  ")");
    }


}
