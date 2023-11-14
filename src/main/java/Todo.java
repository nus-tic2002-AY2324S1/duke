public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDisplay() {
        return "[T]" + super.toDisplay();
    }

    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
