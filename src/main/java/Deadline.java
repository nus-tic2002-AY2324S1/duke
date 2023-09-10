public class Deadline extends Task{
    String by;
    public Deadline(String description, String by){
        super(description);
        this.type = 'D';
        this.by = by;
    }
    @Override
    public String getDescription(){
        return (description + " (by: " + by + ")");
    }
}
