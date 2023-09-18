public class Deadline extends Todo {
    protected String duedate;

    Deadline(String description, String by) {
        super(description);
        duedate = by;
    }

    public void setBy(String change) {
        duedate = change;
    }

    public String getBy() {
        return duedate;
    }
}

