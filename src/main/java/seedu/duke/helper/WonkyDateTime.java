package seedu.duke.helper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts strings to LocalDateTime objects.
 */
public class WonkyDateTime {
    

    /**
     * Stores a mapping of strings to LocalDateTime objects. The strings are used to represent specific dates and times.
     */
    public final static Map<String, LocalDateTime> STR_TO_DATE_TIME_MAPPING = new HashMap<String, LocalDateTime>() {
        {
            put("today", LocalDateTime.now());
            put("tomorrow", LocalDateTime.now().plusDays(1));
            put("tmr", LocalDateTime.now().plusDays(1));
            put("mon", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
            put("monday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
            put("tues", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
            put("tuesday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
            put("wed", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)));
            put("wednesday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)));
            put("thur", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
            put("thursday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
            put("fri", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
            put("friday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
            put("sat", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
            put("saturday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
            put("sun", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
            put("sunday", LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
        }
    };
    
    private final FormatStyle FORMAT_STYLE = FormatStyle.MEDIUM;
    private LocalDateTime dateTime;

    public final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a WonkyDateTime object with the specified LocalDateTime.
     *
     * @param dateTime the LocalDateTime to be used by the WonkyDateTime object.
     */
    public WonkyDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Constructs a WonkyDateTime object with the specified natural date string.
     *
     * @param naturalDateStr the LocalDateTime to be used by the WonkyDateTime object.
     */
    public WonkyDateTime(String naturalDateStr) {
        this.dateTime = STR_TO_DATE_TIME_MAPPING.get(naturalDateStr.toLowerCase());
    }

    /**
     * Returns the LocalDateTime object used by the WonkyDateTime object.
     *
     * @return the LocalDateTime object used by the WonkyDateTime object.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the format style set by the setFormatStyle() method.
     *
     * @return a string representation of the LocalDateTime object used by the WonkyDateTime object.
     */
    public String getStyledDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FORMAT_STYLE));
    }

    /**
     * Returns a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the default format "yyyy-MM-dd HH:mm".
     *
     * @return a string representation of the LocalDateTime object used by the WonkyDateTime object.
     */
    public String getDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
