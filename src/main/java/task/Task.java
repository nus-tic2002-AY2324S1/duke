package task;

/**
 * Represents a task.
 */
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

    public String toStorageString() {
        String doneMarker = this.getIsDone() ? "1" : "0";
        return doneMarker + " | " + desc;
    }

    /**
     * Returns a string representation of the task in the format:
     * "[X] description" if the task is marked as done,
     * or "[ ] description" if the task is not done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String doneMarker = this.getIsDone() ? "X" : " ";
        return "[" + doneMarker + "] " + desc;
    }
}
