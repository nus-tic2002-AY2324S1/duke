package tim.tasks;

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
