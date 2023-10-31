package tim.tasks;

/**
 * This class is a subclass of Task.
 * It is used to create a ToDo object.
 * It has a description.
 *
 * @param description the description of the task
 */
public class ToDo extends Task {
    public ToDo (String description){
        super(description);
        this.setType('T');
    }
    @Override
    public String getDescription(){
        return super.getDescription() ;
    }
}
