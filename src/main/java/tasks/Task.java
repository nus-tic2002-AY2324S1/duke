package tasks;

public abstract class Task {
    protected String type;
    protected String description;
    protected String status;
    protected final String SELECT = "[✓] ";
    protected final String UNSELECT = "[ ] ";
    protected static int listSize;

    public Task(String description) {
        type = "";
        this.description = description.toUpperCase();
        status = UNSELECT;
        listSize++;
    }

    public abstract String getTask();

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setSelectStatus() {
        this.status = SELECT;
    }

    public void setUnselectStatus() {
        this.status = UNSELECT;
    }

    public String getStatus() {
        return status;
    }

    public static int getListSize() {
        return listSize;
    }
}
