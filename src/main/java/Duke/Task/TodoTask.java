package Duke.Task;

public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super('T', taskName);
    }

    public TodoTask(String taskName, boolean completed) {
        super('T', taskName, completed);
    }
}
