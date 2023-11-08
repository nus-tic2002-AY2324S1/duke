package utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a DateTimeUtils.
 * It is a utility class for handling date and time.
 */
public class DateTimeUtils {

    /**
     * Returns the DateTimeFormatter object.
     * This method will handle the date and time.
     * Use will input the date and time in natural language.
     * It will return the date and time in the correct format.
     *
     * @return The LocalDateTime object
     */
    public static LocalDateTime parseNextDay(String dateStr) {
        if (!Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun", "monday", "tuesday", "wednesday",
                           "thursday", "friday", "saturday", "sunday", "today", "tmr", "tomorrow", "eoy", "end of year",
                           "eom", "end of month").contains(dateStr.toLowerCase())) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(23);
        now = now.withMinute(59);
        now = now.withSecond(59);
        if (dateStr.toLowerCase().contains("today")) {
            return now;
        }
        boolean isTomorrow = dateStr.toLowerCase().contains("tomorrow") || dateStr.toLowerCase().contains("tmr");
        if (isTomorrow) {
            return now.plusDays(1);
        }
        boolean isEndOfYear = dateStr.toLowerCase().contains("eoy") || dateStr.toLowerCase().contains("end of year");
        if (isEndOfYear) {
            return now.withMonth(12).withDayOfMonth(31);
        }
        boolean isEndOfMonth = dateStr.toLowerCase().contains("eom") || dateStr.toLowerCase().contains("end of month");
        if (isEndOfMonth) {
            return now.withDayOfMonth(now.getMonth().maxLength());
        }
        List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.values());
        int dayIndex = dayOfWeeks.indexOf(DayOfWeek.valueOf(dateStr.toUpperCase()));
        int currentDayIndex = now.getDayOfWeek().getValue();
        int daysToAdd = dayIndex - currentDayIndex;
        if (daysToAdd < 1) {
            daysToAdd += 7;
        }
        // set time to the end of the day
        return now.plusDays(daysToAdd);

    }

}
