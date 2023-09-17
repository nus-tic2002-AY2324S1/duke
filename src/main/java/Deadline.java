public class Deadline extends Task{
    protected String by;

    public Deadline(){}
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        abbreviation = 'D';

        responseGotIt();
        Duke.indentation();
        System.out.print(" ");
        System.out.println(this);
        responseNumberOfTasks();
    }


    @Override
    public String toString() {
        return String.format("[%c][%s] %s (by: %s)",abbreviation,getStatusIcon(),description, by);
    }
}
