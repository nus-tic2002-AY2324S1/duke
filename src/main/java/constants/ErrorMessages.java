package constants;

public class ErrorMessages {
    public static final String TASK_NOT_FOUND = "Non-Existent Task.";
    public static final String EMPTY_LIST = "Current list is empty.";
    public static final String INVALID_DATE_FORMAT =
            "Invalid date format. Please provide the date in this format: YYYY-MM-DD.";
    public static final String MISSING_ITEM_NUMBER = "Missing item number!";
    public static final String MISSING_EVENT_DESCRIPTION = "Missing event description!";
    public static final String MISSING_TODO_DESCRIPTION = "Missing todo description!";
    public static final String INVALID_INTEGER = "Ensure the item number is a valid integer!";
    public static final String MISSING_DATE = "Please provide a date!";
    public static final String INVALID_COMMAND_FORMAT = "Please follow the correct format.";
    public static final String INVALID_TODO_FORMAT = "Please follow the correct format for adding To Dos.";
    public static final String INVALID_TODO_WITHIN_PERIOD_FORMAT = "Please follow the correct format for adding To Dos Within Period tasks.";
    public static final String INVALID_DELETE_COMMAND_FORMAT =
            "Please follow the correct format for deleting Tasks.";
    public static final String INVALID_DEADLINE_COMMAND_FORMAT =
            "Please follow the correct format for adding Deadlines. Your date should also be in this format: YYYY-MM-DD.";
    public static final String INVALID_EVENT_COMMAND_FORMAT =
            "Please follow the correct format for adding Events. Both dates should also be in this format: YYYY-MM-DD.";
    public static final String INVALID_MARK_COMMAND_FORMAT =
            "Please follow the correct format for marking tasks. Ensure you pass in a valid integer.";
    public static final String INVALID_UNMARK_COMMAND_FORMAT =
            "Please follow the correct format for marking tasks. Ensure you pass in a valid integer.";
    public static final String INVALID_DUE_COMMAND_FORMAT =
            "Please follow the correct format for checking items due. Dates should be in this format: YYYY-MM-DD.";
    public static final String INVALID_FIND_COMMAND_FORMAT = "Please provide only one keyword at a time.";
    public static final String INVALID_PRIORITY = "Please choose from: Low, Medium or High.";
    public static final String TRAILING_LETTERS_BEHIND_BY = "Ensure you have no trailing letters behind /by.";
    public static final String TRAILING_LETTERS_BEHIND_FROM =
            "Ensure you have no trailing letters behind /from.";
    public static final String TRAILING_LETTERS_BEHIND_TO = "Ensure you have no trailing letters behind /to.";
    public static final String ERROR_END_DATE_BEFORE_START_DATE =
            "Your End Date should be later than your Start Date.";
    public static final String INVALID_USER_INPUT =
            "Oops! Invalid input, look at the manual to see the accepted inputs!";
}
