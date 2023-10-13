package tim;

public class ToDo extends Task{
    public ToDo (String description){
        super(description);
        this.type = 'T';
    }
    @Override
    public String getDescription(){
        return (description);
    }
}
