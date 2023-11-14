import java.util.ArrayList;

public class TaskList extends Task{

    private ArrayList<Task> taskList = new ArrayList<>(100);

    public TaskList(String description) {
        super(description);
    }
}
