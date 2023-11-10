package duke.parser;

import duke.command.Command;
import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.regex.Matcher;

/**
 * The Parser class is responsible for interpreting user input.
 * It provides methods for parsing user input into commands, tasks, or other data structures used by the application.
 */
public class Parser {
    public static String MESSAGE_INCORRECT_MONTH = "Invalid month: %s . Month must be between 1 and 12.";
    public static String MESSAGE_INCORRECT_DAY = "Invalid day: %s for month %s and year %s";
    public static String MESSAGE_INCORRECT_HOUR = "Invalid hour: %s . Hour must be between 0 and 23.";
    public static String MESSAGE_INCORRECT_MINUTE = "Invalid minute: %s . Minute must be between 0 and 59.";
    public static int MAX_MONTH = 12;
    public static int MIN_MONTH = 1;
    public static int MAX_HOUR = 23;
    public static int MAX_MINUTE = 59;
    public static int MIN_HOUR_MINUTE = 0;

    /**
     * Parses a user keyword argument and returns the corresponding Command object.
     *
     * @param keywordArgument The user keyword argument to be parsed.
     * @return The Command object corresponding to the parsed keyword argument, or null if parsing fails.
     */
    public static Command parseKeywordToCommand(UserKeywordArgument keywordArgument) {
        try {
            KeywordTypes key = KeywordTypes.valueOf(keywordArgument.getKeyword().toUpperCase());
            return key.createCommand();
        } catch (IllegalArgumentException e) {
            Ui ui = new Ui();
            ui.showResponseToUser(Message.MESSAGE_I_DONT_KNOW);
            return null;
        }
    }

    /**
     * Parses a user input string and returns the first word as the parsed keyword.
     *
     * @param userInput The user input string to be parsed.
     * @return The first word of the input string, representing the parsed keyword.
     */
    public static String parseKeyword(String userInput) {
        String[] inputs = userInput.split(" ");
        return inputs[0];
    }

    /**
     * Parses a user input string and returns the argument portion after the first word.
     *
     * @param userInput The user input string to be parsed.
     * @return The argument portion of the input string, excluding the first word.
     */
    public static String parseArgument(String userInput) {
        String[] inputs = userInput.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            sb.append(inputs[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Parses a string and returns a boolean value.
     *
     * @param string The input string to be parsed.
     * @return true if the input string equals "1", indicating a true boolean value, false otherwise.
     */
    public static boolean parseStringToBoolean(String string) {
        return string.equals("1");
    }

    /**
     * Checks if a given string can be parsed into an integer.
     *
     * @param s The input string to be checked.
     * @return true if the input string can be parsed into an integer, false otherwise.
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Constructs and returns a LocalDateTime object using matchers for date and time components.
     *
     * @param dateMatcher The Matcher object for matching date components (year, month, day).
     * @param timeMatcher The Matcher object for matching time components (hour, minute).
     * @return The constructed LocalDateTime object based on the matched date and time components.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    public static LocalDateTime constructDateTime(Matcher dateMatcher, Matcher timeMatcher) throws InvalidArgumentException {
        final int year = Integer.parseInt(dateMatcher.group("year"));
        final int month = Integer.parseInt(dateMatcher.group("month"));
        final int day = Integer.parseInt(dateMatcher.group("day"));
        final int hour = Integer.parseInt(timeMatcher.group("hour"));
        final int minute = Integer.parseInt(timeMatcher.group("minute"));
        dateValidation(year, month, day);
        timeValidation(hour, minute);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Constructs and returns a LocalDateTime object using a matcher for date components with time set to midnight
     * (00:00).
     *
     * @param dateMatcher The Matcher object for matching date components (year, month, day).
     * @return The constructed LocalDateTime object with date components and time set to midnight.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    public static LocalDateTime constructDateTime(Matcher dateMatcher) throws InvalidArgumentException {
        final int year = Integer.parseInt(dateMatcher.group("year"));
        final int month = Integer.parseInt(dateMatcher.group("month"));
        final int day = Integer.parseInt(dateMatcher.group("day"));
        final int hour = 0;
        final int minute = 0;
        dateValidation(year, month, day);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Validates the given year, month, and day values, ensuring they form a valid date.
     *
     * @param year  The year to validate.
     * @param month The month to validate.
     * @param day   The day to validate.
     * @throws InvalidArgumentException If the month or day is outside the acceptable range for the given year.
     */
    public static void dateValidation(int year, int month, int day) throws InvalidArgumentException {
        if (month < MIN_MONTH || month > MAX_MONTH) {
            String errMsg = String.format(MESSAGE_INCORRECT_MONTH, month);
            throw new InvalidArgumentException(errMsg);
        } else if (day < 1 || day > getMaxDayOfMonth(year, month)) {
            String errMsg = String.format(MESSAGE_INCORRECT_DAY, day, month, year);
            throw new InvalidArgumentException(errMsg);
        }
    }

    /**
     * Validates the given hour and minute values, ensuring they are within the acceptable range.
     *
     * @param hour   The hour to validate.
     * @param minute The minute to validate.
     * @throws InvalidArgumentException If the hour or minute is outside the acceptable range.
     */
    public static void timeValidation(int hour, int minute) throws InvalidArgumentException {
        if (hour < MIN_HOUR_MINUTE || hour > MAX_HOUR) {
            String errMsg = String.format(MESSAGE_INCORRECT_HOUR, hour);
            throw new InvalidArgumentException(errMsg);
        } else if (minute < MIN_HOUR_MINUTE || minute > MAX_MINUTE) {
            String errMsg = String.format(MESSAGE_INCORRECT_MINUTE, minute);
            throw new InvalidArgumentException(errMsg);
        }
    }

    /**
     * Gets the maximum number of days in the specified month of the given year.
     *
     * @param year  The year for which to determine the maximum days.
     * @param month The month for which to determine the maximum days (1-based index).
     * @return The maximum number of days in the specified month of the given year.
     */
    public static int getMaxDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
