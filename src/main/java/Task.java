public class Task {
    protected String description;
    protected boolean isDone;

    private static int totalTasks;
    
    public static int getTotalTasks(){
        return totalTasks;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks ++;
    }

    
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkTask(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}
