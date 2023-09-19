public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public String isDone() {
        return (isDone ? "X" : " ");
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }
}