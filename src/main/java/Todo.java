public class Todo extends Task{
    public Todo(String description) {
        super(description);
        abbreviation = 'T';

        responseGotIt();
        Duke.indentation();
        System.out.print(" ");
        System.out.println(this);
        responseNumberOfTasks();
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s",abbreviation,getStatusIcon(),description);
    }
}
