package parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import UI.ListTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    /**
     * Original print stream and new print stream placeholders
     */
    private static final PrintStream originalSystem = System.out;
    private static final ByteArrayOutputStream newSystem = new ByteArrayOutputStream();
    /**
     * sets system out to a new print stream for string capture
     */
    @BeforeAll
    public static void newSystem(){
        System.setOut(new PrintStream(newSystem));
    }

    /**
     * sets system out back to its original settings
     */
    @AfterAll
    public static void previousSystem(){
        System.setOut(originalSystem);
    }
    @Test
    public void dateFormatValid() {
        String dateStr = "19/11/2023 23:59";
        boolean isNotValid = Parser.dateFormatInvalid(dateStr);
        Assertions.assertFalse(isNotValid);
    }

    @Test
    public void dateFormatInvalid() {
        String dateStr = "19/9/2023 23:59";
        boolean isNotValid = Parser.dateFormatInvalid(dateStr);
        Assertions.assertTrue(isNotValid);
    }

    @Test
    public void todoTest() {
        ListTask list = new ListTask();
        String line = "todo create test case";
        Assertions.assertTrue(Parser.isCorrect(Keyword.TODO, line, list));
    }
    @Test
    public void markTest_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "mark 2";
        String Response = "Task number is larger than the list , add more task";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.MARK, line, list));
    }
    @Test
    public void unmarkTest_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "unmark 0";
        String Response = "Only real numbers please";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.MARK, line, list));
    }
    @Test
    public void deleteTest_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "unmark 0";
        String Response = "Only real numbers please";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.MARK, line, list));
    }
    @Test
    public void deadlineTest() {
        ListTask list = new ListTask();
        String line = "deadline create test case /by 19/11/2023 23:59";
        Assertions.assertTrue(Parser.isCorrect(Keyword.DEADLINE, line, list));
    }

    @Test
    public void deadline_missingContent_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "deadline /by 19/11/2023 23:59";
        String Response = "Where is the Deadline context and due time?";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.DEADLINE, line, list));
    }
    @Test
    public void deadline_invalidByDateTime_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "deadline check for invalid date time /by 9/9/2023 23:59";
        String Response = "Please use the format dd/MM/yyyy HH:mm with valid Date for due date";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.DEADLINE, line, list));
    }
    @Test
    public void eventTest() {
        ListTask list = new ListTask();
        String line = "event test case time line /from 12/11/2023 00:00 /to 19/11/2023 23:59";
        Assertions.assertTrue(Parser.isCorrect(Keyword.EVENT, line, list));
    }
    @Test
    public void event_missingFromDate_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "event test case time line /from /to 19/11/2023 23:59";
        String Response = "Does the Event not end? , add /to";
        boolean Success = newSystem.toString().contains(Response);
        Assertions.assertEquals(Success,Parser.isCorrect(Keyword.EVENT, line, list));
    }
    @Test
    public void event_invalidFromDateTime_exceptionThrown() {
        ListTask list = new ListTask();
        String line = "event test case time line /from 29/02/2023 00:00 /to 9/11/2023 23:59";
        String Response1 = "Please use the format dd/MM/yyyy HH:mm with valid Date for start date";
        String Response2 = "Please use the format dd/MM/yyyy HH:mm with valid Date for end date";
        boolean Success1 = newSystem.toString().contains(Response1);
        boolean Success2 = newSystem.toString().contains(Response2);
        Assertions.assertEquals(Success1,Parser.isCorrect(Keyword.EVENT, line, list));
        Assertions.assertEquals(Success2,Parser.isCorrect(Keyword.EVENT, line, list));
    }
}


