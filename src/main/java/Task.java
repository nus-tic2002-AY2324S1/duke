public class Task {
    protected String description;
    protected Boolean marked;

    public void Task(String description){
        this.description = description;
        this.marked = false;
    }
    public String getStatusIcon(){
        return (marked ? "X" : " ");
    }

}
