package duke.tasks;
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority p = Priority.LOW;
    
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

    public String getDescription(){
        return this.description;
    }

    public void setTaskPriority(Priority p){
        this.p = p;
    }

    public String getPriority(){
        String priority = this.p.name();
        String formatted = priority.substring(0, 1) + priority.substring(1).toLowerCase();
        return formatted.trim();
    }
    
    public String toString(){
        return  "[" + getStatusIcon() + "]" + "[" + getPriority() + "] " + description;
    }

    public String writeFile(){
        String text = p + "|"  + isDone + "|" + description ;
        return text;
    }
}
