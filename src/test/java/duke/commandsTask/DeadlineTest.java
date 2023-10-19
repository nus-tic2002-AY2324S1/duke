package duke.commandsTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private final String cmd = "deadline";
    private final String desc = "bake a cake";

    private String by = "";
    private String myCode = "";
    private String myString = "";

    @Test
    public void deadlineMark(){
        by = "tomorrow";
        Task deadline = new Deadline(true, desc, by);
        myCode = cmd + " -m-" + desc + " /by " + by;
        myString = "[D][X] " + desc + " (by: " + by + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }

    @Test
    public void deadlineUnmark(){
        by = "tomorrow";
        Task deadline = new Deadline(false, desc, by);
        myCode = cmd + " " + desc + " /by " + by;
        myString = "[D][ ] " + desc + " (by: " + by + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }

    @Test
    public void deadlineDate1(){
        by = "2023-01-23";
        Task deadline = new Deadline(false, desc, by);
        myCode = cmd + " " + desc + " /by " + "Jan 23 2023";
        myString = "[D][ ] " + desc + " (by: " + "Jan 23 2023" + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }

    @Test
    public void deadlineDate2(){
        by = "2023-13-23";
        Task deadline = new Deadline(false, desc, by);
        myCode = cmd + " " + desc + " /by " + by;
        myString = "[D][ ] " + desc + " (by: " + by + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }

    @Test
    public void deadlineDateTime1(){
        by = "2023-01-23 1859";
        Task deadline = new Deadline(false, desc, by);
        myCode = cmd + " " + desc + " /by " + "Jan 23 2023, 6:59PM";
        myString = "[D][ ] " + desc + " (by: " + "Jan 23 2023, 6:59PM" + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }

    @Test
    public void deadlineDateTime2(){
        by = "2023-01-23 2559";
        Task deadline = new Deadline(false, desc, by);
        myCode = cmd + " " + desc + " /by " + by;
        myString = "[D][ ] " + desc + " (by: " + by + ")";
        assertAll("returnString",
                ()->assertEquals(myCode, deadline.toCode()),
                ()->assertEquals(myString, deadline.toString())
        );
    }
}
