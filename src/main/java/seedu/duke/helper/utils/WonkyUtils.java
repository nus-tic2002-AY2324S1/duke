package seedu.duke.helper.utils;

import java.util.Objects;

import seedu.duke.helper.WonkyDateTime;

/**
 *  Contains utility methods.
 */
public class WonkyUtils {
    /**
     * Checks if the given string is a valid natural date and time string.
     *
     * @param str the string to be checked
     * @return true if the string is a valid natural date and time string, false otherwise
     */
    public static boolean isValidNaturalDateTimeStr(String str) {
        return Objects.nonNull(WonkyDateTime.STR_TO_DATE_TIME_MAPPING.get(str.toLowerCase()));
    }
}
