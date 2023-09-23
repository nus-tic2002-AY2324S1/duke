public class Delete extends IndexBaseCommand{

    public Delete(){}
    public Delete (String taskDescription){
        process(taskDescription);
    }
    @Override
    public String message() {
        return "Noted. I've removed this task:";
    }
}
