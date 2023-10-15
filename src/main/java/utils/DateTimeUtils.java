package utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DateTimeUtils {

    public static LocalDateTime parseNextDay(String dateStr) {
        if (!Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun",
                        "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "today")
                .contains(dateStr.toLowerCase())) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(23);
        now = now.withMinute(59);
        if(dateStr.toLowerCase().contains("today")){
            return now;
        }
        List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.values());
        int dayIndex = dayOfWeeks.indexOf(DayOfWeek.valueOf(dateStr.toUpperCase()));
        int currentDayIndex = now.getDayOfWeek().getValue();
        int daysToAdd = dayIndex - currentDayIndex;
        if (daysToAdd < 1) {
            daysToAdd += 7;
        }
        // set time to end of day
        return now.plusDays(daysToAdd);

    }

}
