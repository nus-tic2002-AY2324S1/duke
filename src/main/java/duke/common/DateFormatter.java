package duke.common;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {

    public static String toDate(String input){
        Matcher match;
        try {
            Pattern DATE_FORMAT = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
            match = DATE_FORMAT.matcher(input);
            if (match.matches()){
                return LocalDate.parse(input).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            }

            Pattern DATETIME_FORMAT = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{4})");
            match = DATETIME_FORMAT.matcher(input);
            if (match.matches()){
                return LocalDateTime.parse(input,DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")).format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:ma"));
            }
        }
        catch (DateTimeException ignore){

        }
        return input;
    }
}
