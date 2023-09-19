public abstract class Task {
    protected String description;
    protected char type;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
        type = '-';
    }

    public abstract String getDescription();

    public String getIsDone(){
        return isDone ? "x" : " ";
    }

    public void setIsDone(boolean markUnmark){
        isDone = markUnmark;
    }

    public char getType (){
        return type;
    }
}
