public class Task {

    protected String taskDescription;
    protected String type;
    protected boolean isDone;

    public Task (String Description){
        this.taskDescription = Description;
        this.isDone = false;
        this.type = "Task";
    }


    public String getIsDone(){
        if (this.isDone == false) {
            return "Not Done";
        } else {
            return "Done";
        }
    }

    public void markDone(){
        this.isDone = true;
    }
    public void unmarkDone(){
        this.isDone = false;
    }

    public String toString() {
        return "[Todo] [" + getIsDone() + "] " + this.taskDescription;
    }

}
