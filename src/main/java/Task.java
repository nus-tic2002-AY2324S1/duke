public class Task {
    protected String description;
    protected Boolean marked;
    public Task(String description){
        this.description = description;
        marked = false;
    }
    public String getStatusIcon(){
        return (this.marked ? "X" : " ");
    }
    public String getDescription(){
        return description;
    }
    public static void setMarked(Task task){
        task.marked = true;
    }
    public static void setUnmarked(Task task){
        task.marked = false;
    }
}
