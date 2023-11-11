package tasks;
public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkTask(){
        this.isDone = false;
        System.out.println("Task marked as Done!");
    }

    public Boolean isMarked(){
        return isDone;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }


    public String writeFile(){
        String text = "T," + isDone + "," + description ;
        return text;
    }
}
