package Task;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Stores and creates date and time for tasks
 * Child class of SpecialTask
 */
public class DateTask extends SpecialTask {
    public LocalDate deadline;
    public DateTask(String type, String description, String deadline) {
        super(type, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.deadline = LocalDate.parse(deadline, formatter);
    }
    public void replaceDate(String newdeadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.deadline = LocalDate.parse(newdeadline, formatter);
    }
    public String showDate() {
        String date = "";
        if (deadline != null) {
            date = " (" + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return date;
    }
    public static String dateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datestring = formatter.format(date);
        return datestring;
    }
}
