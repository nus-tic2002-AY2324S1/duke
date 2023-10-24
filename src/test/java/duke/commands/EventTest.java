package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private final String cmd = "event";
    private final String desc = "bake a cake";
    private String from = "Saturday";
    private String to = "Sunday";

    private String myCode = "";
    private String myString = "";

    @Test
    public void eventMark(){
        Task event = new Event(true, desc, from, to);
        myCode = cmd + " -m-" + desc + " /from " + from + " /to " + to;
        myString = "[E][X] " + desc + " (from: " + from + " to: " + to + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }

    @Test
    public void eventUnmark(){
        Task event = new Event(false, desc, from, to);
        myCode = cmd + " " + desc + " /from " + from + " /to " + to;
        myString = "[E][ ] " + desc + " (from: " + from + " to: " + to + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }

    @Test
    public void eventDate1(){
        from = "2023-01-23";
        to = "2023-01-24";
        Task event = new Event(false, desc, from, to);
        myCode = cmd + " " + desc + " /from " + "Jan 23 2023" + " /to " + "Jan 24 2023";
        myString = "[E][ ] " + desc + " (from: " + "Jan 23 2023" + " to: " + "Jan 24 2023" + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }

    @Test
    public void eventDate2(){
        from = "2023-13-23";
        to = "2023-13-24";
        Task event = new Event(false, desc, from, to);
        myCode = cmd + " " + desc + " /from " + from + " /to " + to;
        myString = "[E][ ] " + desc + " (from: " + from + " to: " + to + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }

    @Test
    public void eventDateTime1(){
        from = "2023-01-23 1859";
        to = "2023-01-24 2130";
        Task event = new Event(false, desc, from, to);
        myCode = cmd + " " + desc + " /from " + "Jan 23 2023, 6:59PM" + " /to " + "Jan 24 2023, 9:30PM";
        myString = "[E][ ] " + desc + " (from: " + "Jan 23 2023, 6:59PM" + " to: " + "Jan 24 2023, 9:30PM" + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }

    @Test
    public void eventDateTime2(){
        from = "2023-01-23 2559";
        to = "2023-01-24 3030";
        Task event = new Event(false, desc, from, to);
        myCode = cmd + " " + desc + " /from " + from + " /to " + to;
        myString = "[E][ ] " + desc + " (from: " + from + " to: " + to + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, event.toCode()),
                ()->assertEquals(myString, event.toString())
        );
    }
}
