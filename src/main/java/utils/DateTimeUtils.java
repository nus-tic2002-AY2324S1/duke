package utils;

import static io.CrabyMessage.printDateTimeParseExceptionMessage;
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
     * @param dateTimeStr the time string from the user.
     * @return The LocalDateTime object
     */
    public static LocalDateTime parseNextDay(String dateTimeStr) {
        LocalDateTime now = LocalDateTime.now();
        int index = dateTimeStr.indexOf(" ");
        String getDate;
        String getTimes = "";
        if (index == -1) {
            getDate = dateTimeStr;
        } else {
            getDate = dateTimeStr.substring(0, index).trim();
            getTimes = dateTimeStr.substring(index).trim();
        }

        if (!Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun", "monday", "tuesday", "wednesday",
                           "thursday", "friday", "saturday", "sunday", "today", "tmr", "tomorrow", "eoy", "end-of-year",
                           "eom", "end-of-month").contains(getDate.toLowerCase())) {
            return null;
        }
        if (getTimes.isBlank()) {
            now = now.withHour(23);
            now = now.withMinute(59);
        } else {
            try {
                now = now.withHour(Integer.parseInt(getTimes.substring(0, 2)));
                now = now.withMinute(Integer.parseInt(getTimes.substring(2)));
            } catch (Exception e) {
                return null;
            }
        }
        LocalDateTime now1 = specialCase(dateTimeStr, now);
        if (now1 != null) {
            return now1;
        }
        int daysToAdd = dayOfWeek(dateTimeStr, now);
        return now.plusDays(daysToAdd);
    }

    private static int dayOfWeek(String dateTimeStr, LocalDateTime now) {
        List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.values());
        int dayIndex = dayOfWeeks.indexOf(DayOfWeek.valueOf(dateTimeStr.toUpperCase()));
        int currentDayIndex = now.getDayOfWeek().getValue();
        int daysToAdd = dayIndex - currentDayIndex;
        if (daysToAdd < 1) {
            daysToAdd += 7;
        }
        return daysToAdd;
    }

    private static LocalDateTime specialCase(String dateTimeStr, LocalDateTime now) {
        if (dateTimeStr.toLowerCase().contains("today")) {
            return now;
        }
        boolean isTomorrow =
                dateTimeStr.toLowerCase().contains("tomorrow") || dateTimeStr.toLowerCase().contains("tmr");
        if (isTomorrow) {
            return now.plusDays(1);
        }
        boolean isEndOfYear =
                dateTimeStr.toLowerCase().contains("eoy") || dateTimeStr.toLowerCase().contains("end-of-year");
        if (isEndOfYear) {
            return now.withMonth(12).withDayOfMonth(31);
        }
        boolean isEndOfMonth =
                dateTimeStr.toLowerCase().contains("eom") || dateTimeStr.toLowerCase().contains("end-of-month");
        if (isEndOfMonth) {
            return now.withDayOfMonth(now.getMonth().maxLength());
        }
        return null;
    }

}
