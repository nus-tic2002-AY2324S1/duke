public abstract class Task {
    protected String description;
    protected  boolean isDone;
    protected char abbreviation;
    private static int numberOfTasks = 0;

    public Task(String description){
        this.description = description;
        isDone = false;

        numberOfTasks++;
    }

    protected static void responseNumberOfTasks() {
        Duke.indentation();
        System.out.printf("Now you have %d %s in the list.\n",numberOfTasks,numberOfTasks>1?"tasks":"task");
        Duke.line();
    }

    protected static void responseGotIt() {
        Duke.line();
        Duke.indentation();
        System.out.println("Got it. I've added this task:");
    }

    public String getStatusIcon(){
        return (isDone? "X":" ");
    }

    public void markAsDone(boolean done){
        this.isDone = done;
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

    @Override
    public abstract String toString();
    /*public String toString(){
        return String.format("[%s] %s",getStatusIcon(),description);
    }*/
}
