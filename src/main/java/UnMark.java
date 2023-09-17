public class UnMark extends Mark implements ICommand{
    public UnMark(){
        isMark = false;
    }

    public UnMark(String taskDescription){
        this.isMark = false;
        process(taskDescription);
    }
}
