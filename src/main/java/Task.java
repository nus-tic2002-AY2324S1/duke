public class Task {
    private boolean isDone;
    private String description;

    public Task (String s,boolean d){
        this.isDone = d;
        this.description = s;
    }
    // get task isDone and get taskString
    public void setIsDone(boolean x){
        this.isDone = x;
    }

    public void setDescription(String s){
        this.description = s;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription(){
        return description;
    }

}
