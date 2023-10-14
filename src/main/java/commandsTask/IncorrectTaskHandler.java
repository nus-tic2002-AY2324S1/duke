package commandsTask;

public class IncorrectTaskHandler extends Task{
    public IncorrectTaskHandler(String desc){
        this.description = desc;
        this.toRecord = false;
        this.toUpdateList = false;
    }
    @Override
    public String toString(){
        return description;
    }
}
