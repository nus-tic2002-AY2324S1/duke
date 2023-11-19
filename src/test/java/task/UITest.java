package task;

import UI.ListTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parser.Keyword;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static UI.UI.response;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {
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
    public void mark_responseTest() {
        ListTask list = new ListTask();
        String First = "First todo";
        list.add(new Todo(First));
        int number = 0;
        String Response = "Excellent , Cate marks your Task";
        response(Keyword.MARK,list,number);
        boolean Success = newSystem.toString().contains(Response);
        assertTrue(Success);
    }
    @Test
    public void unmark_responseTest() {
        ListTask list = new ListTask();
        String First = "First todo";
        list.add(new Todo(First));
        int number = 0;
        String Response = "Don't worry , Cate un-marks your Task";
        response(Keyword.UNMARK,list,number);
        boolean Success = newSystem.toString().contains(Response);
        assertTrue(Success);
    }
    @Test
    public void todo_responseTest() {
        ListTask list = new ListTask();
        String line = "First todo";
        list.add(new Todo(line));
        int number = 0;
        String Response = "Just do it";
        response(Keyword.TODO,list,number);
        boolean Success = newSystem.toString().contains(Response);
        assertTrue(Success);
    }
    @Test
    public void deadline_responseTest() {
        ListTask list = new ListTask();
        String line = "First deadline";
        String byDate = "19/11/2023 23:59";
        LocalDateTime by = Parser.constructDateTime(byDate);
        list.add(new Deadline(line,by));
        int number = 0;
        String Response = "Time is ticking";
        response(Keyword.DEADLINE,list,number);
        boolean Success = newSystem.toString().contains(Response);
        assertTrue(Success);
    }
    @Test
    public void event_responseTest() {
        ListTask list = new ListTask();
        String line = "First event";
        String fromDate = "12/11/2023 23:59";
        LocalDateTime from = Parser.constructDateTime(fromDate);
        String toDate = "19/11/2023 23:59";
        LocalDateTime to = Parser.constructDateTime(toDate);
        list.add(new Event(line,from,to));
        int number = 0;
        String Response = "Track the duration";
        response(Keyword.EVENT,list,number);
        boolean Success = newSystem.toString().contains(Response);
        assertTrue(Success);
    }
}
