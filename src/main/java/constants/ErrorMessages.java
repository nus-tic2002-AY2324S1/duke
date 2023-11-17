package constants;

public class ErrorMessages {
        public static final String EMPTY_LIST =
                        "Your task list is currently clear! Time for a new adventure.";
        public static final String ERROR_END_DATE_BEFORE_START_DATE =
                        "Whoops! Your End Date should come after the Start Date.";
        public static final String TASK_NOT_FOUND =
                        "Apologies, I couldn't find the task. Double-check your task list and try again.";
        public static final String INVALID_DATE_FORMAT =
                        "Whoops! Invalid date format. Please use YYYY-MM-DD for dates.";
        public static final String INVALID_COMMAND_FORMAT =
                        "Hmm, something seems off. Make sure to follow the correct command format.";
        public static final String INVALID_TODO_FORMAT =
                        "Sorry, the format for adding To-Dos isn't quite right. Please check the manual.";
        public static final String INVALID_TODO_WITHIN_PERIOD_FORMAT =
                        "My apologies! Follow the correct format for adding To-Dos Within Period tasks.";
        public static final String INVALID_DELETE_COMMAND_FORMAT =
                        "Oops! The delete command seems a bit scrambled. Double-check the format and try again.";
        public static final String INVALID_DEADLINE_COMMAND_FORMAT =
                        "Hold on! Follow the correct format for adding Deadlines, and ensure the date is in YYYY-MM-DD format.";
        public static final String INVALID_EVENT_COMMAND_FORMAT =
                        "Almost there! Follow the correct format for adding Events, and make sure both dates are in YYYY-MM-DD format.";
        public static final String INVALID_MARK_COMMAND_FORMAT =
                        "Something's amiss! Follow the correct format for marking tasks. Make sure to pass in a valid integer.";
        public static final String INVALID_UNMARK_COMMAND_FORMAT =
                        "Hmm, marking tasks? Follow the correct format and ensure to pass in a valid integer.";
        public static final String INVALID_DATE =
                        "Oh snap! It seems you've stumbled upon a date from an alternate dimension. Check the calendar, because the date you provided doesn't exist in this realm!";
        public static final String INVALID_DUE_COMMAND_FORMAT =
                        "Hold up! Follow the correct format for checking items due. Dates should be in YYYY-MM-DD format.";
        public static final String INVALID_FIND_COMMAND_FORMAT = "Oops! One keyword at a time, please.";
        public static final String INVALID_PRIORITY =
                        "Choose wisely! Priority should be either Low, Medium, or High.";
        public static final String INVALID_USER_INPUT =
                        "Uh-oh! That's not quite right. Check the manual for accepted inputs and try again.";
        public static final String ERROR_MARKING_MARKED_TASK =
                        "Whoa there, adventurer! Attempting to mark an already marked task? Double the glory, I suppose!";
        public static final String ERROR_UMARKING_UNMARKED_TASK =
                        "Hold your horses! Attempting to unmark a task that was never in the victory parade?";
}
