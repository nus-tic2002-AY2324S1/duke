package Task;

public class Task {
    protected String description;
    protected Boolean isMarked;
    public Task(String description){
        this.description = description;
        isMarked = false;
    }
    public String getStatusIcon(){
        return (this.isMarked ? "X" : " ");
    }
    public String getDescription(){
        return description;
    }
    public static void setMarked(Task task){
        task.isMarked = true;
    }
    public static void setUnmarked(Task task){
        task.isMarked = false;
    }
}
