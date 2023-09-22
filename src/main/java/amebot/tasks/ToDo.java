package amebot.tasks;

public class ToDo extends Task {
    protected final String TODO = "[TODO] ";

    public ToDo(String desc) {
        super(desc);
        super.type = TODO;
    }

    @Override
    public void printTask() {
        System.out.println(type + status + desc);
    }
}
