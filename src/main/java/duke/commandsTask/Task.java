package duke.commandsTask;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean toRecord;
    protected boolean toUpdateList;

    public Task(){ }

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.toRecord = true;
        this.toUpdateList = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }
    public String getDescription() { return this.description; }
    public boolean getIsDone() { return this.isDone; }
    public void setIsDone(boolean isDone){ this.isDone = isDone; }
    public boolean getToRecord() { return this.toRecord; }
    public boolean getToUpdateList() { return this.toUpdateList; }

    public String toCode() {
        return ((isDone) ? "m " : "") + description;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
