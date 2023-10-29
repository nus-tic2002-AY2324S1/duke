package task;

public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc){
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone){
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String doneMarker = " ";
        if (isDone) {
            doneMarker = "X";
        }
        return "[" + doneMarker + "] " + desc;
    }
}
