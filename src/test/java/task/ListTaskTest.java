package task;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parser.Parser;
import UI.ListTask;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ListTaskTest {
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
    public void sortTest(){
        ListTask testList = new ListTask();
        String First = "First todo";
        String Second = "Second deadline with latest end date";
        String Third = "Third event with second earliest end date";
        String Fourth = "Fourth event with earliest end date";
        testList.add(new Todo(First));
        testList.add(new Deadline(Second, Parser.constructDateTime("19/11/2023 23:59")));
        testList.add(new Event(Third, Parser.constructDateTime("16/11/2023 23:59"),Parser.constructDateTime("18/11/2023 23:59")));
        testList.add(new Event(Fourth, Parser.constructDateTime("12/11/2023 23:59"),Parser.constructDateTime("15/11/2023 23:59")));
        testList.sortSchedule();
        String secondInList = testList.get(1).getDescription();
        assertEquals(secondInList,Fourth);
    }
    @Test
    public void findTest(){
        ListTask testList = new ListTask();
        String First = "First todo";
        String Second = "Second deadline with latest end date";
        String Third = "Third event with second earliest end date";
        String Fourth = "Fourth event with earliest end date";
        testList.add(new Todo(First));
        testList.add(new Deadline(Second, Parser.constructDateTime("19/11/2023 23:59")));
        testList.add(new Event(Third, Parser.constructDateTime("16/11/2023 23:59"),Parser.constructDateTime("18/11/2023 23:59")));
        testList.add(new Event(Fourth, Parser.constructDateTime("12/11/2023 23:59"),Parser.constructDateTime("15/11/2023 23:59")));
        testList.findTask("Second");
        boolean Success = newSystem.toString().contains(Second);
        assertTrue(Success);
    }
    @Test
    public void viewTest(){
        ListTask testList = new ListTask();
        String First = "First todo";
        String Second = "Second deadline with latest end date";
        String Third = "Third event with second earliest end date";
        String Fourth = "Fourth event with earliest end date";
        testList.add(new Todo(First));
        testList.add(new Deadline(Second, Parser.constructDateTime("19/11/2023 23:59")));
        testList.add(new Event(Third, Parser.constructDateTime("16/11/2023 23:59"),Parser.constructDateTime("18/11/2023 23:59")));
        testList.add(new Event(Fourth, Parser.constructDateTime("12/11/2023 23:59"),Parser.constructDateTime("15/11/2023 23:59")));
        testList.viewSchedule("17/11/2023");
        boolean Success1 = newSystem.toString().contains(Second);
        boolean Success2 = newSystem.toString().contains(Third);
        assertTrue(Success1);
        assertTrue(Success2);
    }

}
