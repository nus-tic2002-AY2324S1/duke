package amebot.parser;

import amebot.common.Regex;
import amebot.commands.Command;
import amebot.tasks.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseToDo_Task() {
        String command = "todo task 1~";
        int endIndex = command.length();
        Command parsedEvent = new Parser().parseTodo(command, endIndex);
        ArrayList<String> logs = parsedEvent.executeCommand();
        assertEquals("[TODO] [ ] TASK 1~", logs.get(0));
    }

    @Test
    public void parseEvent_Date_Time_Task() {
        String command = "event task 1~ /from 2023-11-25 0835 /to 2023-11-25 2355";
        int endIndex = command.length();
        Command parsedEvent = new Parser().parseEvent(command, endIndex);
        ArrayList<String> logs = parsedEvent.executeCommand();
        assertEquals("[EVENT] [ ] TASK 1~ (FROM: 25 NOV 2023 (SAT) 8:35AM TO: 25 NOV 2023 (SAT) 11:55PM)", logs.get(0));
    }

    @Test
    public void parseEvent_Date_Task() {
        String command = "event task 1~ /from 2023-11-25 /to 2023-11-26";
        int endIndex = command.length();
        Command parsedEvent = new Parser().parseEvent(command, endIndex);
        ArrayList<String> logs = parsedEvent.executeCommand();
        assertEquals("[EVENT] [ ] TASK 1~ (FROM: 25 NOV 2023 (SAT) TO: 26 NOV 2023 (SUN))", logs.get(0));
    }

    @Test
    public void parseDeadline_Date_Time_Task() {
        String command = "deadline task 1~ /due 2023-11-25 2310";
        int endIndex = command.length();
        Command parsedEvent = new Parser().parseDeadline(command, endIndex);
        ArrayList<String> logs = parsedEvent.executeCommand();
        assertEquals("[DEADLINE] [ ] TASK 1~ (DUE: 25 NOV 2023 (SAT) 11:10PM)", logs.get(0));
    }

    @Test
    public void parseDeadline_Date_Task() {
        String command = "deadline task 1~ /due 2023-11-25";
        int endIndex = command.length();
        Command parsedEvent = new Parser().parseDeadline(command, endIndex);
        ArrayList<String> logs = parsedEvent.executeCommand();
        assertEquals("[DEADLINE] [ ] TASK 1~ (DUE: 25 NOV 2023 (SAT))", logs.get(0));
    }
}
