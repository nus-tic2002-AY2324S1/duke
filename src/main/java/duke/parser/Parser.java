package duke.parser;

import duke.command.Command;
import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Matcher;


public class Parser {
    public Parser(){}
    public static Command parse(UserKeywordArgument keywordArgument) {
        try {
            KeywordTypes key = KeywordTypes.valueOf(keywordArgument.getKeyword().toUpperCase());
            return key.createCommand();
        } catch (IllegalArgumentException e) {
            Ui ui = new Ui();
            ui.showResponseToUser(Message.MESSAGE_I_DONT_KNOW);
            return null;
        }
    }
    public static Command parse(String keyword){
        try {
            KeywordTypes key = KeywordTypes.valueOf(keyword.toUpperCase());
            return key.createCommand();
        } catch (IllegalArgumentException e) {
            Ui ui = new Ui();
            ui.showResponseToUser(Message.MESSAGE_I_DONT_KNOW);
            return null;
        }
    }
    public static String parseKeyword(String userInput){
        String[] inputs = userInput.split(" ");
        return inputs[0];
    }
    public static String parseArgument(String userInput){
        String[] inputs = userInput.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            sb.append(inputs[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public static boolean parseStringToBoolean(String string){
        return string.equals("1");
    }
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static LocalDateTime dateTime(Matcher dateMatcher, Matcher timeMatcher) {
        final int year = Integer.parseInt(dateMatcher.group("year"));
        final int month = Integer.parseInt(dateMatcher.group("month"));
        final int day = Integer.parseInt(dateMatcher.group("day"));
        final int hour = Integer.parseInt(timeMatcher.group("hour"));
        final int minute = Integer.parseInt(timeMatcher.group("minute"));
        return LocalDateTime.of(year,month,day,hour,minute);
    }
}
