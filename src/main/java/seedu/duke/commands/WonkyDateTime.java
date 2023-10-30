package seedu.duke.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that maps strings to LocalDateTime objects and provides various methods to manipulate and format
 * LocalDateTime objects.
 */
public class WonkyDateTime {

    /**
     * A mapping of strings to LocalDateTime objects. The strings are used to represent specific dates and times.
     */
    final static private Map<String, LocalDateTime> STR_TO_DATE_TIME_MAPPING = new HashMap<String, LocalDateTime>() {
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

    private LocalDateTime dateTime;
    private static FormatStyle formatStyle = FormatStyle.MEDIUM;
    private static DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a WonkyDateTime object with the specified LocalDateTime.
     *
     * @param dateTime the LocalDateTime to be used by the WonkyDateTime object.
     */
    public WonkyDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
     * Sets the format style used by the getStyledDateTimeStr() method.
     *
     * @param format the format style to be used by the getStyledDateTimeStr() method.
     */
    public static void setFormatStyle(FormatStyle format) {
        formatStyle = format;
    }

    /**
     * Returns the format style used by the getStyledDateTimeStr() method.
     *
     * @return the format style used by the getStyledDateTimeStr() method.
     */
    public static FormatStyle getFormatStyle() {
        return formatStyle;
    }

    /**
     * Returns the default DateTimeFormatter used by the getDateTimeStr() method.
     *
     * @return the default DateTimeFormatter used by the getDateTimeStr() method.
     */
    public static DateTimeFormatter getDtf() {
        return defaultFormat;
    }

    /**
     * Returns a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the format style set by the setFormatStyle() method.
     *
     * @return a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the format style set by the setFormatStyle() method.
     */
    public String getStyledDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(formatStyle));
    }

    /**
     * Returns a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the default format "yyyy-MM-dd HH:mm".
     *
     * @return a string representation of the LocalDateTime object used by the WonkyDateTime object, formatted
     * according to the default format "yyyy-MM-dd HH:mm".
     */
    public String getDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the LocalDateTime object mapped to the specified string in the STR_TO_DATE_TIME_MAPPING map.
     *
     * @param str the string to be mapped to a LocalDateTime object.
     * @return the LocalDateTime object mapped to the specified string in the STR_TO_DATE_TIME_MAPPING map.
     */
    public static LocalDateTime getMappedDateTimeStr(String str) {
        return STR_TO_DATE_TIME_MAPPING.get(str);
    }
}
