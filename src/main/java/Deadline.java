public class Deadline extends Task {

    protected String doneBefore;


    public Deadline(String description, String doneBefore){
        super(description);
        this.doneBefore = doneBefore;
        this.isDone = false;
        this.type = "Deadline";
    }


    public String toString() {
        return "[Deadline] [" + getIsDone() + "] " + this.taskDescription + " (" + this.doneBefore + ")";
    }
}
