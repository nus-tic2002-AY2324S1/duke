public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }
    public String getDescription() { return this.description; }
    public boolean getIsDone() { return this.isDone; }
    public void setIsDone(boolean isDone){ this.isDone = isDone; }


    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
