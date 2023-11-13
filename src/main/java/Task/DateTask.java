package Task;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTask extends SpecialTask {
    protected LocalDateTime deadline;
    public DateTask(String description, String type, String deadline) {
        super(description, type);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    public String getDate(){
        String date = " ("+ deadline.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
        return date;
    }
}
