package amebot.tasks;

public abstract class Task {
    protected String type;
    protected String desc;
    protected String status;
    protected final String SELECT = "[✓] ";
    protected final String UNSELECT = "[ ] ";
    protected static int listSize;

    public Task(String desc) {
        type = "";
        this.desc = desc.toUpperCase();
        status = UNSELECT;
        listSize++;
    }

    public void printTask() {
        System.out.println(type + status + desc);
    }

    public String getTaskType() {
        return type;
    }

    public String getTaskDesc() {
        return desc;
    }

    public String setTaskStatus(String status) {
        String message = "";

        if (status.equals("check")) {
            this.status = SELECT;
            message = "You've completed this item!";
        } else {
            this.status = UNSELECT;
            message = "Pending item!";
        }

        return message;
    }

    public String getTaskStatus() {
        return status;
    }

    public static int getTaskListSize() {
        return listSize;
    }
}
