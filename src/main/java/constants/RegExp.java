package constants;

public class RegExp {
    public static final String STRICT_DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static final String DATE_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";
    public static final String SET_COMMAND_FORMAT_REGEX = "^set (\\d+) to (.+)$";
    public static final String DEADLINE_COMMAND_FORMAT_REGEX = "^deadline (.+) /by " + DATE_REGEX + "$";
    public static final String EVENT_COMMAND_FORMAT_REGEX = "^event (.+) /from (" + DATE_REGEX + ") /to (" + DATE_REGEX + ")$";
    public static final String SPACE_DELIMITER = " ";
    public static final String LINE_DELIMITER = "\\|";
    public static final String NEW_LINE = "\n";
}