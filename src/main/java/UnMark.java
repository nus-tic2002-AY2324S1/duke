public class UnMark extends Mark{
    public UnMark(){
        isMark = false;
    }

    public UnMark(String taskDescription){
        this.isMark = false;
        process(taskDescription);
    }

    @Override
    public String message() {
        return "OK, I've marked this task as not done yet:";
    }
}
