package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String Desc){ this.description = Desc; }
    public String getDescription(){ return this.description; }

    public boolean getStatus(){
        return isDone;
    }

    public void markAsDone(){ this.isDone = true; }
    public void unmarkDone(){ this.isDone = false; }

    public String getStatusIcon() {  return isDone? "[X]" : "[ ]"; } // mark done task with X
    public abstract TaskType getTaskType();
}