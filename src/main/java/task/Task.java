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

    public boolean getDone(){
        return isDone;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public String toStorageString() {
        String doneMarker = this.getDone() ? "1" : "0";
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
        String doneMarker = this.getDone() ? "X" : " ";
        return "[" + doneMarker + "] " + desc;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());
    }
}
