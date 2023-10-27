package duke.parser;

import duke.command.Command;
import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Matcher;


public class Parser {
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
     */
    public static LocalDateTime constructDateTime(Matcher dateMatcher, Matcher timeMatcher) {
        final int year = Integer.parseInt(dateMatcher.group("year"));
        final int month = Integer.parseInt(dateMatcher.group("month"));
        final int day = Integer.parseInt(dateMatcher.group("day"));
        final int hour = Integer.parseInt(timeMatcher.group("hour"));
        final int minute = Integer.parseInt(timeMatcher.group("minute"));
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Constructs and returns a LocalDateTime object using a matcher for date components with time set to midnight
     * (00:00).
     *
     * @param dateMatcher The Matcher object for matching date components (year, month, day).
     * @return The constructed LocalDateTime object with date components and time set to midnight.
     */
    public static LocalDateTime constructDateTime(Matcher dateMatcher) {
        final int year = Integer.parseInt(dateMatcher.group("year"));
        final int month = Integer.parseInt(dateMatcher.group("month"));
        final int day = Integer.parseInt(dateMatcher.group("day"));
        final int hour = 0;
        final int minute = 0;
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
