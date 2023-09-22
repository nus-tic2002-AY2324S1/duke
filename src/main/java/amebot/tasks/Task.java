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

    public abstract void printTask();

    public void setTaskType(String type) {
        this.type = type;
    }

    public String getTaskType() {
        return type;
    }

    public void setTaskDesc(String desc) {
        this.desc = desc;
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
