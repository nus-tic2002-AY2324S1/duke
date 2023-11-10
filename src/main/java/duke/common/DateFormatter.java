package duke.common;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateFormatter class
 */
public class DateFormatter {

    private static final Pattern DATE_FORMAT = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
    private static final Pattern DATETIME_FORMAT = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{4})");
    /**
     * Returns a string format of a date it recognizes
     *
     * @param input string that may contain a date
     * @return String reformatted date
     */
    public static String toDate(String input) {
        Matcher match;
        try {
            match = DATE_FORMAT.matcher(input);
            if (match.matches()) {
                return LocalDate.parse(input).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            }

            match = DATETIME_FORMAT.matcher(input);
            if (match.matches()) {
                return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"))
                        .format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:ma"));
            }
        } catch (DateTimeException ignore) {
            return input;
        }
        return input;
    }
}
