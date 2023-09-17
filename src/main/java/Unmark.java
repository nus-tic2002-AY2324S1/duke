public class Unmark extends Mark implements ICommand{
    public Unmark(){
        isMark = false;
    }

    public Unmark(String taskDescription){
        this.isMark = false;
        process(taskDescription);
    }
}
