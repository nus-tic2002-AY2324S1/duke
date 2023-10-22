package nus.duke.common;

/**
 * The `Utils` class provides utility methods for common operations.
 */
public class Utils {
    /**
     * Attempts to parse an integer from a given string.
     *
     * @param s The string to parse as an integer.
     * @return An `Integer` object representing the parsed integer, or `null` if parsing fails.
     */
    public static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
