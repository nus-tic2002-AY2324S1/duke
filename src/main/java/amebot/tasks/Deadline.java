package amebot.tasks;

public class Deadline extends Task {
    protected final String DEADLINE = "[DEADLINE] ";
    protected String due;

    public Deadline(String desc, String due) {
        super(desc);
        super.type = DEADLINE;
        this.due = due.toUpperCase();
    }

    @Override
    public void printTask() {
        System.out.println(type + status + desc + due);
    }
}
