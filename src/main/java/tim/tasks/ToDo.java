package tim.tasks;

/**
 * Represents as a ToDo object.
 * This class is a subclass of Task.
 * It has a description.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object.
     *
     * @param description the description of the task.
     */
    public ToDo (String description){
        super(description);
        this.setType('T');
    }

}
