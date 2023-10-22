package tim.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String description, String by){
        super(description);
        this.by = LocalDate.parse(by);
        this.setType('D');
    }
    @Override
    public String getDescription(){
        return (super.getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
