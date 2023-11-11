package amebot.parser;

import amebot.common.Regex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {
    @Test
    public void parseFromAndToDateTime_Task() {
        String command = "event task 1~ /from 2023-11-25 0835 /to 2023-11-25 2355";
        int startIndexOfFromDateTime = command.indexOf(Regex.FROM_PATTERN);
        int endIndex = command.length();
        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfFromDateTime, endIndex);
        String fromDateTime = parsedDateTime.get(0);
        assertEquals(" (from: 25 Nov 2023 (Sat) 8:35AM", fromDateTime);
        String toDateTime = parsedDateTime.get(1);
        assertEquals(" to: 25 Nov 2023 (Sat) 11:55PM)", toDateTime);
    }

    @Test
    public void parseFromAndToDate_Task() {
        String command = "event task 1~ /from 2023-11-25 /to 2023-11-25";
        int startIndexOfFromDateTime = command.indexOf(Regex.FROM_PATTERN);
        int endIndex = command.length();
        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfFromDateTime, endIndex);
        String fromDateTime = parsedDateTime.get(0);
        assertEquals(" (from: 25 Nov 2023 (Sat)", fromDateTime);
        String toDateTime = parsedDateTime.get(1);
        assertEquals(" to: 25 Nov 2023 (Sat))", toDateTime);
    }

    @Test
    public void parseDueDateTime_Task() {
        String command = "deadline task 1~ /due 2023-11-25 2310";
        int startIndexOfFromDateTime = command.indexOf(Regex.DUE_PATTERN);
        int endIndex = command.length();
        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfFromDateTime, endIndex);
        String dueDateTime = parsedDateTime.get(0);
        assertEquals(" (due: 25 Nov 2023 (Sat) 11:10PM)", dueDateTime);
    }

    @Test
    public void parseDueDate_Task() {
        String command = "deadline task 1~ /due 2023-11-25";
        int startIndexOfFromDateTime = command.indexOf(Regex.DUE_PATTERN);
        int endIndex = command.length();
        ArrayList<String> parsedDateTime = new DateTimeParser().parseDateTime(command, startIndexOfFromDateTime, endIndex);
        String dueDateTime = parsedDateTime.get(0);
        assertEquals(" (due: 25 Nov 2023 (Sat))", dueDateTime);
    }
}
