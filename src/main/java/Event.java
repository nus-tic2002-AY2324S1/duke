public class Event extends Task{
    String from;
    String to;
    public Event(String description, String from, String to){
        super(description);
        this.type = 'E';
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription(){
        return (description + "(from: " + from + " to: " + to +  ")");
    }


}
