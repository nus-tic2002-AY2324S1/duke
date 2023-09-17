public class Event extends Task{

    protected String from;
    protected String to;

    public Event(){}
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
        abbreviation = 'E';

        responseGotIt();
        Duke.indentation();
        System.out.print(" ");
        System.out.println(this);
        responseNumberOfTasks();
    }

    @Override
    public String toString(){
        return String.format("[%c][%s] %s (from: %s to: %s)",abbreviation,getStatusIcon(),description, from, to);
    }
}
