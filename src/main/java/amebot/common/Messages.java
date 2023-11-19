package amebot.common;

/**
 * Messages class contains all the messages that will be displayed to the user.
 */
public class Messages {
    public static final String TOP_BORDER = "________________________________________________________________________________";
    public static final String BOTTOM_BORDER = "--------------------------------------------------------------------------------" + System.lineSeparator();
    public static final String FILE_NOT_FOUND = "amebot.txt file is not found in directory!";
    public static final String FOLDER_CREATED = "Created a data folder!";
    public static final String FILE_CREATED = "Created amebot.txt file in data folder!";
    public static final String SUCCESS_LOAD_TASK = "Successfully loaded tasks from storage!";
    public static final String SUCCESS_SAVE_TASK = "Successfully saved tasks to storage!";
    public static final String INVALID_FILE_FORMAT = "Invalid file format! Please save as text file format~";
    public static final String EMPTY_FILE = "File content is empty!";
    public static final String EMPTY_LINE = "Current line is empty!";
    public static final String INVALID_COMMAND = "You've entered an invalid command!";
    public static final String INVALID_FIND_KEYWORD = "Keyword is empty!";
    public static final String MATCHING_ITEMS = "Matching item(s) in current list:";
    public static final String NO_MATCHING_ITEMS = "Keyword does not match any item(s) in current list!";
    public static final String INVALID_UPDATE = "Your selected task does not support this update! Please enter a valid update!";
    public static final String DESCRIPTION_UPDATED = "Description updated successfully!";
    public static final String DATE_TIME_UPDATED = "DateTime updated successfully!";
    public static final String INVALID_DESC = "Description is empty!";
    public static final String INVALID_DESC_DATE = "Description is empty or you've entered an invalid format!";
    public static final String INVALID_DATE = "Please enter yyyy-MM-dd or dd/MM/yyyy (HHmm is optional)~";
    public static final String INVALID_DATE_TIME_RANGE = "Invalid date or time range!";
    public static final String INVALID_DATE_TIME = "From dateTime must be earlier than To dateTime!";
    public static final String INVALID_INDEX = "Index is empty! Please select an index from 1 to ";
    public static final String INVALID_INDEX_VALUE = "Index is out of bound! Please select an index from 1 to ";
    public static final String STATUS_MARKED = "You've completed this item!";
    public static final String STATUS_UNMARKED = "Pending item";
    public static final String EMPTY_LIST = "List is empty!";
    public static final String SUCCESS_ADD_MESSAGE = "Successfully added to list!";
    public static final String SUCCESS_REMOVE_MESSAGE = "Successfully removed from list!";
    public static final String CURRENT_LIST = " item(s) in list~";
    public static final String FULL_LIST = "All item(s) in current list:";
    public static final String ERROR_MESSAGE = "File is not executable!";
    public static final String RENDER_ERROR = "Error rendering application!";
    public static final String EXIT_MESSAGE = "Thanks for using AMEBOT~!";
}
