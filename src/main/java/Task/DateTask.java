package Task;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Stores and creates date and time for tasks
 * Child class of SpecialTask
 */
public class DateTask extends SpecialTask {
    public LocalDateTime deadline;
    public DateTask(String type, String description, String deadline) {
        super(type, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }
    public String showDate() {
        String date = "";
        if (deadline != null) {
            date = " (" + deadline.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
        }
        return date;
    }
    public static String dateToString(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = formatter.format(date);
        return datestring;
    }
}
