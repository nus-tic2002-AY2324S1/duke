package seedu.duke.helper.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeManagerException;
import seedu.duke.helper.WonkyDateTime;
import seedu.duke.io.WonkyLogger;

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

    /**
     * Checks if a given string is a valid date and time in the format specified by {@link WonkyDateTime#getDtf()}.
     * If the string is not a valid date and time, it checks if it is a valid date in the format specified by
     * {@link WonkyDateTime#getMappedDateTimeStr(String)}.
     * If the string is not a valid date or date and time,
     * it logs an error message using {@link wonkyLogger#expectedDateTime(String)}.
     *
     * @param str the string to be checked.
     * @return true if the string is a valid date or date and time, false otherwise.
     * @throws DukeException if there is an error parsing the string as a date or date and time.
     */
    public static boolean isValidDateTime(String str) throws DukeException {
        str = str.trim();
        try {
            LocalDateTime.parse(str, WonkyDateTime.dtf);
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(new WonkyDateTime(str))) {
                return true;
            }
            WonkyLogger.getInstance().expectedDateTime(str);
            return false;
        }
        return true;
    }

    /**
     * Parses a string to a WonkyDateTime object.
     *
     * @param str the string to be parsed.
     * @return a WonkyDateTime object representing the parsed date and time.
     * @throws DukeException if the string cannot be parsed into a valid date and time.
     */
    public static WonkyDateTime parseToDate(String str) throws DukeException {
        str = str.trim();
        try {
            return new WonkyDateTime(LocalDateTime.parse(str, WonkyDateTime.dtf));
        } catch (DateTimeParseException e) {
            if (Objects.nonNull(WonkyUtils.isValidNaturalDateTimeStr(str))) {
                return new WonkyDateTime(str);
            }
            throw new DukeManagerException("Invalid date value.");
        } catch (Exception e) {
            throw new DukeManagerException(e);
        }
    }
}
