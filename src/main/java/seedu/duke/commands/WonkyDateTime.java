package seedu.duke.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

public class WonkyDateTime {

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

    public WonkyDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static void setFormatStyle(FormatStyle format) {
        formatStyle = format;
    }

    public static FormatStyle getFormatStyle() {
        return formatStyle;
    }

    public static DateTimeFormatter getDtf() {
        return defaultFormat;
    }

    public String getStyledDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(formatStyle));
    }

    public String getDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static LocalDateTime getMappedDateTimeStr(String str) {
        return STR_TO_DATE_TIME_MAPPING.get(str);
    }
}
