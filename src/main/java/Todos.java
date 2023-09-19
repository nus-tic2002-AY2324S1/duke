public class Todos extends Task{

    public Todos(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

}
