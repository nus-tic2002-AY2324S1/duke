package constants;

public class RegExp {
    public static final String STRICT_DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static final String DATE_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";
    public static final String SET_COMMAND_FORMAT_REGEX = "^set\\s+(\\d+)\\s+to\\s+(.+)$";
    public static final String DEADLINE_COMMAND_FORMAT_REGEX = "^deadline\\s+(.+)\\s+/by\\s+(" + DATE_REGEX + ")$";
    public static final String EVENT_COMMAND_FORMAT_REGEX = "^event\\s+(.+)\\s+/from\\s+(" + DATE_REGEX + ")\\s+/to\\s+(" + DATE_REGEX + ")$";
    public static final String TODO_COMMAND_FORMAT_REGEX = "^todo\\s+(.+)$";
    public static final String TODO_COMMAND_WITHIN_PERIOD_FORMAT_REGEX = "^todo\\s+(.+)\\s+/between\\s+(" + DATE_REGEX + ")\\s+/and\\s+(" + DATE_REGEX + ")$";
    public static final String DELETE_COMMAND_FORMAT_REGEX = "^delete\\s+(\\d+)$";
    public static final String MARK_COMMAND_FORMAT_REGEX = "^mark\\s+(\\d+)$";
    public static final String UNMARK_COMMAND_FORMAT_REGEX = "^unmark\\s+(\\d+)$";
    public static final String DUE_COMMAND_FORMAT_REGEX = "^due\\s+" + DATE_REGEX + "$";
    public static final String FIND_COMMAND_FORMAT_REGEX = "^find\\s+(.+)$";
    public static final String FILE_STORAGE_EVENT_FORMAT = "E\\|(LOW|MEDIUM|HIGH)\\|(true|false)\\|(.+)\\|" + DATE_REGEX + "\\|" + DATE_REGEX;
    public static final String FILE_STORAGE_DEADLINE_FORMAT = "D\\|(LOW|MEDIUM|HIGH)\\|(true|false)\\|(.+)\\|" + DATE_REGEX;
    public static final String FILE_STORAGE_TODO_FORMAT = "T\\|(LOW|MEDIUM|HIGH)\\|(true|false)\\|(.+)";
    public static final String FILE_STORAGE_TODO_WITHIN_PERIOD_FORMAT = "T\\|(LOW|MEDIUM|HIGH)\\|(true|false)\\|(.+)\\|" + DATE_REGEX + "\\|" + DATE_REGEX;
    public static final String SPACE_DELIMITER = " ";
    public static final String LINE_DELIMITER = "\\|";
    public static final String NEW_LINE = "\n";
}