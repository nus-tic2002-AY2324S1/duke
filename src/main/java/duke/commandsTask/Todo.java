package duke.commandsTask;

public class Todo extends Task {
    public static final String CMD = "todo";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Create a basic to do task ||"
            + "Syntax: e.g. todo <text>";
    public Todo(boolean isdone, String description){
        super(description);
        setIsDone(isdone);
    }

    @Override
    public String toCode(){
        return CMD + " " + ((isDone) ? "-m-" : "") +  description;
    }

    @Override
    public String toString(){
        return  "[T]" + super.toString();
    }
}
