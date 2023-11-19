package amebot.common;

/**
 * Regex class contains all the regex patterns used in the program.
 */
public class Regex {
    public static final String LIST_COMMAND = "^list$";
    public static final String TODO_COMMAND = "^todo(?!\\s*$).+$";
    public static final String FIND_COMMAND = "^find(?!\\s*$).+$";
    public static final String EVENT_COMMAND = "^event .+ /from .+ /to .+$";
    public static final String FROM_PATTERN = " /from ";
    public static final String TO_PATTERN = " /to ";
    public static final String DEADLINE_COMMAND = "^deadline .+ /due .+$";
    public static final String DUE_PATTERN = " /due ";
    public static final String DATE_TIME_PATTERN = "(\\d{4}-\\d{2}-\\d{2}|\\d{2}/\\d{2}/\\d{4})(?: \\d{4})?";
    public static final String UPDATE_DATE_TIME_COMMAND = "^update [1-9]\\d* (\\/from|\\/to|\\/due) (\\d{4}-\\d{2}-\\d{2}|\\d{2}\\/\\d{2}\\/\\d{4})(?: \\d{4})?$";
    public static final String UPDATE_DESCRIPTION_COMMAND = "^update [1-9]\\d* (?!\\s*$).+$";
    public static final String MARK_INDEX_COMMAND = "^(mark|unmark) [1-9]\\d*$";
    public static final String REMOVE_INDEX_COMMAND = "^remove [1-9]\\d*$";
    public static final String BYE_COMMAND = "^bye$";
}
